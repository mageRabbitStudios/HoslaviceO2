package com.kinzlstanislav.hoslaviceo2.architecture.repository

import com.kinzlstanislav.hoslaviceo2.architecture.network.api.HoslaviceO2ApiService
import com.kinzlstanislav.hoslaviceo2.architecture.network.response.UsersResponse
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository.UsersFetchResult.*
import com.kinzlstanislav.hoslaviceo2.architecture.repository.database.AppDatabase
import com.kinzlstanislav.hoslaviceo2.architecture.repository.database.UsersDao
import com.kinzlstanislav.hoslaviceo2.architecture.repository.mapper.UsersMapper
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.core.extensions.isConnectionError
import io.mockk.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.ConnectException

class UsersRepositoryTest {

    private companion object {
        val SOME_USERS = listOf(User(0, "", "", false, ""))
    }

    private val mockUsersMapper = mockk<UsersMapper>()
    private val mockDatabase = mockk<AppDatabase>()
    private val mockApi = mockk<HoslaviceO2ApiService>()

    private val mockUsersDao = mockk<UsersDao>(relaxed = true)
    private val mockUsersResponseDeferred = mockk<Deferred<UsersResponse>>()
    private val mockUsersResponse = mockk<UsersResponse>()

    private val subject = UsersRepository(mockUsersMapper, mockDatabase, mockApi)

    @Before
    fun setup() {
        mockkStatic("com.kinzlstanislav.hoslaviceo2.core.extensions.ExceptionKtxKt")
        every { mockDatabase.usersDao() } returns mockUsersDao
    }
    @Test
    fun `fetchUsersOrGetLocal() - maps, saves into db, results in OnlineUsers`() {
        every { mockApi.getUsersAsync() } returns mockUsersResponseDeferred
        coEvery { mockUsersResponseDeferred.await() } returns mockUsersResponse
        every { mockUsersMapper.mapUsers(mockUsersResponse) } returns SOME_USERS

        val result = runBlocking { subject.fetchUsersOrGetLocal() }

        coVerifyOrder {
            with(mockUsersDao) {
                nukeTable()
                insertAll(SOME_USERS)
            }
        }

        assertThat(result).isEqualTo(OnlineUsers(SOME_USERS))
    }

    @Test
    fun `fetchUsersOrGetLocal() - maps, inserting throws IOE, results in FetchUsersGenericError`() {
        every { mockApi.getUsersAsync() } returns mockUsersResponseDeferred
        coEvery { mockUsersResponseDeferred.await() } returns mockUsersResponse
        every { mockUsersMapper.mapUsers(mockUsersResponse) } returns SOME_USERS
        coEvery { mockUsersDao.nukeTable() } throws IOException()
        every { Exception().isConnectionError() } returns false

        val result = runBlocking { subject.fetchUsersOrGetLocal() }

        assertThat(result).isEqualTo(FetchUsersGenericError)
    }

    @Test
    fun `fetchUsersOrGetLocal() - api throws connection exception, no users saved, results in NoSavedUsersNetworkError`() {
        every { mockApi.getUsersAsync() } throws ConnectException()
        every { Exception().isConnectionError() } returns true
        coEvery { mockUsersDao.getAll() } returns emptyList()

        val result = runBlocking { subject.fetchUsersOrGetLocal() }

        assertThat(result).isEqualTo(NoSavedUsersNetworkError)
    }

    @Test
    fun `fetchUsersOrGetLocal() - api throws connection exception, users saved, results in OfflineUsers`() {
        every { mockApi.getUsersAsync() } throws ConnectException()
        every { Exception().isConnectionError() } returns true
        coEvery { mockUsersDao.getAll() } returns SOME_USERS

        val result = runBlocking { subject.fetchUsersOrGetLocal() }

        assertThat(result).isEqualTo(OfflineUsers(SOME_USERS))
    }

    @Test
    fun `removeUserFromLocalDb() - user delete called`() {
        runBlocking { subject.removeUserFromLocalDb(SOME_USERS[0]) }
        coVerify {
            mockUsersDao.delete(SOME_USERS[0])
        }
    }
}
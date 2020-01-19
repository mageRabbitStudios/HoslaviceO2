package com.kinzlstanislav.hoslaviceo2.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository.UsersFetchResult
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository.UsersFetchResult.*
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.base.extensions.set
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.*
import com.kinzlstanislav.hoslaviceo2.unittesting.BaseViewModelTest
import io.mockk.*
import org.junit.Test

class ListViewModelTest : BaseViewModelTest() {

    private companion object {
        val SOME_USER =
            User(0, "", "", false, "")
    }
    private val mockUsersRepository = mockk<UsersRepository>()
    private val mockState = mockk<MutableLiveData<ListState>>(relaxed = true)

    private val subject = ListViewModel(mockUsersRepository, mockState)

    @Test
    fun `getUsers() - state set to UsersLoaded`() = getUsersTest(
        resultRepositoryReturns = OnlineUsers(emptyList()),
        desiredOutcomeState = UsersLoaded(emptyList())
    )

    @Test
    fun `getUsers() - state set to UsersLoadedOffline`() = getUsersTest(
        resultRepositoryReturns = OfflineUsers(emptyList()),
        desiredOutcomeState = UsersLoadedOffline(emptyList())
    )

    @Test
    fun `getUsers() - state set to  NetworkError`() = getUsersTest(
        resultRepositoryReturns = NoSavedUsersNetworkError,
        desiredOutcomeState = NetworkError
    )

    @Test
    fun `getUsers() - state set to GenericError`() = getUsersTest(
        resultRepositoryReturns = FetchUsersGenericError,
        desiredOutcomeState = GenericError
    )

    @Test
    fun `removeUserFromLocalDb() - removeUserFromLocalDb is called on repo`() {
        subject.removeUserFromLocalDb(SOME_USER)
        coVerify {
            mockUsersRepository.removeUserFromLocalDb(SOME_USER)
        }
    }

    private fun getUsersTest(
        resultRepositoryReturns: UsersFetchResult,
        desiredOutcomeState: ListState
    ) {
        coEvery { mockUsersRepository.fetchUsersOrGetLocal() } returns resultRepositoryReturns
        subject.getUsers()
        verifyOrder {
            mockState.set(LoadingUsers)
            mockState.set(desiredOutcomeState)
        }
    }

}
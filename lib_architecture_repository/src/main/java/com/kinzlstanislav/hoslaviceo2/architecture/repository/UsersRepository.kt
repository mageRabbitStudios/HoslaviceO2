package com.kinzlstanislav.hoslaviceo2.architecture.repository

import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository.UsersFetchResult.*
import com.kinzlstanislav.hoslaviceo2.architecture.network.api.HoslaviceO2ApiService
import com.kinzlstanislav.hoslaviceo2.architecture.repository.database.AppDatabase
import com.kinzlstanislav.hoslaviceo2.architecture.repository.mapper.UsersMapper
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.core.extensions.isConnectionError
import java.io.IOException

class UsersRepository(
    private val mapper: UsersMapper,
    private val database: AppDatabase,
    private val api: HoslaviceO2ApiService
) {

    sealed class UsersFetchResult {
        data class OfflineUsers(val users: List<User>) : UsersFetchResult()
        data class OnlineUsers(val users: List<User>) : UsersFetchResult()
        object NoSavedUsersNetworkError : UsersFetchResult()
        object FetchUsersGenericError : UsersFetchResult()
    }

    @Throws(IOException::class)
    suspend fun fetchUsersOrGetLocal(): UsersFetchResult = try {
        val users = mapper.mapUsers(api.getUsersAsync().await())
        database.usersDao().apply {
            nukeTable()
            insertAll(users)
        }
        OnlineUsers(users)
    } catch (e: Exception) {
        if (e.isConnectionError()) {
            with(database.usersDao().getAll()) {
                if (isEmpty()) NoSavedUsersNetworkError else OfflineUsers(this)
            }
        } else FetchUsersGenericError
    }

    @Throws(IOException::class)
    suspend fun removeUserFromLocalDb(user: User) {
        database.usersDao().delete(user)
    }
}
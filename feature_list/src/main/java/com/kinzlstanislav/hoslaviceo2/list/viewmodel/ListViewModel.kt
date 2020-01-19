package com.kinzlstanislav.hoslaviceo2.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository.UsersFetchResult.*
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.base.extensions.coroutine
import com.kinzlstanislav.hoslaviceo2.base.extensions.set
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.*
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.GenericError

class ListViewModel(
    private val usersRepository: UsersRepository,
    val state: MutableLiveData<ListState> = MutableLiveData()
) : ViewModel() {

    sealed class ListState {
        data class UsersLoaded(val users: List<User>) : ListState()
        data class UsersLoadedOffline(val users: List<User>) : ListState()

        object LoadingUsers : ListState()
        object NetworkError : ListState()
        object GenericError : ListState()
    }

    init {
        getUsers()
    }

    fun getUsers() {
        coroutine {
            state.apply {
                set(LoadingUsers)
                set(
                    when (val result = usersRepository.fetchUsersOrGetLocal()) {
                        is OnlineUsers -> UsersLoaded(result.users)
                        is OfflineUsers -> UsersLoadedOffline(result.users)
                        is NoSavedUsersNetworkError -> NetworkError
                        is FetchUsersGenericError -> GenericError
                    }
                )
            }
        }
    }

    fun removeUserFromLocalDb(user: User) {
        coroutine {
            usersRepository.removeUserFromLocalDb(user)
        }
    }
}
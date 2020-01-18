package com.kinzlstanislav.hoslaviceo2.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kinzlstanislav.hoslaviceo2.architecture.network.UsersRepository
import com.kinzlstanislav.hoslaviceo2.architecture.network.model.User
import com.kinzlstanislav.hoslaviceo2.base.extensions.coroutine
import com.kinzlstanislav.hoslaviceo2.base.extensions.isConnectionError
import com.kinzlstanislav.hoslaviceo2.base.extensions.set
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.*
import java.lang.Exception

class ListViewModel(
    private val usersRepository: UsersRepository,
    val state: MutableLiveData<ListState> = MutableLiveData()
) : ViewModel() {

    sealed class ListState {
        data class UsersLoaded(val users: List<User>) : ListState()

        object LoadingUsers : ListState()
        object NetworkError : ListState()
        object GenericError : ListState()
    }

    fun getMembers() {
        state.set(LoadingUsers)
        coroutine {
            try {
                state.set(UsersLoaded(usersRepository.fetchUsersOrGetLocal()))
            } catch (e: Exception) {
                state.set(if (e.isConnectionError()) NetworkError else GenericError)
            }
        }
    }
}
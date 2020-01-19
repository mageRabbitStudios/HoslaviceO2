package com.kinzlstanislav.hoslaviceo2.architecture.repository.mapper

import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.architecture.network.response.UsersResponse

class UsersMapper {

    private companion object {
        const val UNKNOWN = "unknown"
    }

    fun mapUsers(response: UsersResponse): List<User> = mutableListOf<User>().apply {
        response.users?.forEach {
            with(it) {
                this@apply.add(
                    User(
                        name = alias
                            ?: UNKNOWN,
                        phoneNumber = phoneNumber
                            ?: UNKNOWN,
                        isMaster = isMaster ?: false,
                        avatarUrl = pictureUrl
                            ?: UNKNOWN
                    )
                )
            }
        }
    }
}
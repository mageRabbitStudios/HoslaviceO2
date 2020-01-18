package com.kinzlstanislav.hoslaviceo2.architecture.network

import com.kinzlstanislav.hoslaviceo2.architecture.network.api.HoslaviceO2ApiService
import com.kinzlstanislav.hoslaviceo2.architecture.network.mapper.UsersMapper
import com.kinzlstanislav.hoslaviceo2.architecture.network.model.User
import java.io.IOException

class UsersRepository(
    private val mapper: UsersMapper,
    private val api: HoslaviceO2ApiService
) {

    @Throws(IOException::class)
    suspend fun fetchUsersOrGetLocal(): List<User> {
        return mapper.mapUsers(api.getUsersAsync().await())
    }

}
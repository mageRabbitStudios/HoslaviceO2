package com.kinzlstanislav.hoslaviceo2.architecture.network.api

import com.kinzlstanislav.hoslaviceo2.architecture.network.response.UsersResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HoslaviceO2ApiService {

    @GET("users.json")
    @Throws(Exception::class)
    fun getUsersAsync(): Deferred<UsersResponse>

}
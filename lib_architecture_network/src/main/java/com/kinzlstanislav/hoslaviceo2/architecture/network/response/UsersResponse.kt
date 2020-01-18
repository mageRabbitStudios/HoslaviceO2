package com.kinzlstanislav.hoslaviceo2.architecture.network.response

import com.squareup.moshi.Json

data class UsersResponse(
    @field:Json(name = "users") val users: List<UserResponse>? = null
)

data class UserResponse(
    @field:Json(name = "global_id") val globalId: String? = null,
    @field:Json(name = "identity_provider") val identityProvider: String? = null,
    @field:Json(name = "alias") val alias: String? = null,
    @field:Json(name = "picture_url") val pictureUrl: String? = null,
    @field:Json(name = "phone_number") val phoneNumber: String? = null,
    @field:Json(name = "is_master") val isMaster: Boolean? = null
)
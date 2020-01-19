package com.kinzlstanislav.hoslaviceo2.architecture.repository.mapper

import com.kinzlstanislav.hoslaviceo2.architecture.network.response.UserResponse
import com.kinzlstanislav.hoslaviceo2.architecture.network.response.UsersResponse
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UsersMapperTest {

    private companion object {
        val INPUT = UsersResponse(
            users = listOf(
                UserResponse(
                    globalId = "",
                    identityProvider = "",
                    alias = "Jarda",
                    pictureUrl = "some_url",
                    phoneNumber = "123",
                    isMaster = true
                ),
                UserResponse(
                    globalId = null,
                    identityProvider = null,
                    alias = null,
                    pictureUrl = null,
                    phoneNumber = null,
                    isMaster = null
                )
            )
        )
        val EXPECTED_OUTPUT = listOf(
            User(
                userId = 0,
                name = "Jarda",
                phoneNumber = "123",
                isMaster = true,
                avatarUrl = "some_url"
            ),
            User(
                userId = 0,
                name = "unknown",
                phoneNumber = "unknown",
                isMaster = false,
                avatarUrl = "unknown"
            )
        )
    }

    private val subject = UsersMapper()

    @Test
    fun `mapUsers() - maps correctly`() {
        assertThat(subject.mapUsers(INPUT)).isEqualTo(EXPECTED_OUTPUT)
    }
}
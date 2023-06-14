package com.journey.bangkit.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginJobSeekerResponse(
    val status: String,
    val id_user: String,
    val role_id: Int,
    val token: String
)

data class LoginJobProviderResponse(
    val status: String,
    val id_company: String,
    val role_id: Int,
    val token: String
)
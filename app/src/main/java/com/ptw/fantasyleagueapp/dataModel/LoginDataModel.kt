package com.ptw.fantasyleagueapp.dataModel

data class LoginDataModel(
    val id: Any,
    val message: String,
    val result: List<LoginResult>
)

data class LoginResult(
    val access_token: String,
    val token_type: String
)
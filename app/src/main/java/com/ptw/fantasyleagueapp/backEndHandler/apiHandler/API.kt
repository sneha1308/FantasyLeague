package com.ptw.fantasyleagueapp.backEndHandler.apiHandler

import com.ptw.fantasyleagueapp.dataModel.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val BaseURL = "https://reqres.in/api/"

interface API {
    @GET("users")
    fun getUserList(@Query("page") page: Int): Single<UserListDataModel>
}
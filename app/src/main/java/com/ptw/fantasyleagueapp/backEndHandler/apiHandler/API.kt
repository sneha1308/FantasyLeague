package com.ptw.fantasyleagueapp.backEndHandler.apiHandler

import com.ptw.fantasyleagueapp.dataModel.LoginDataModel
import com.ptw.fantasyleagueapp.dataModel.LoginResult
import com.ptw.fantasyleagueapp.dataModel.UserListDataModel
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

const val BaseURL = "http://35.154.247.199:8000/"

interface API {

    //Getting the list of tournaments
    @GET("tournaments/")
    fun getTournamentsList(@Header("Authorization") auth: String): Single<UserListDataModel>


    //Posting the login Credentials to get the access token API
    @POST("users/auth")
    fun login(@Body tokenRequest: RequestBody
    ): Single<LoginDataModel>


}
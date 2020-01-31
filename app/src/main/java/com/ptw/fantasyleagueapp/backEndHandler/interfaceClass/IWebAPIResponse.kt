package com.ptw.fantasyleagueapp.backEndHandler.interfaceClass

import com.ptw.fantasyleagueapp.backEndHandler.apiHandler.APIType


interface IWebAPIResponse {
    fun onSuccess(response: Any, type: APIType)
    fun onFailure(errorTxt: String)
}
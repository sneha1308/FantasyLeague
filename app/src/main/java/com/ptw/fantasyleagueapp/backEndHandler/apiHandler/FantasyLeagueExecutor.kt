package com.ptw.fantasyleagueapp.backEndHandler.apiHandler

import android.util.Log
import com.ptw.fantasyleagueapp.backEndHandler.interfaceClass.IWebAPIResponse
import com.ptw.fantasyleagueapp.dataModel.LoginDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

enum class APIType {
    UserLogin,
    tounamentsList;
}

class FantasyLeagueExecutor(var iWebAPIResponse: IWebAPIResponse) {

    private var mCompositeDisposable = CompositeDisposable()

    fun callAPI(type: APIType, accessToken: String, provider: String) {

        when (type) {
            APIType.UserLogin -> {
                val jsonObject = JSONObject();
                jsonObject.put("token", accessToken)
                jsonObject.put("provider", provider)
                mCompositeDisposable.add(
                    NetworkHandler.getInstance().create()
                        .login(getTest(jsonObject))
                        .subscribeOn(Schedulers.io()).observeOn(
                            AndroidSchedulers.mainThread()
                        ).subscribeWith(object : DisposableSingleObserver<LoginDataModel>() {
                            override fun onError(e: Throwable) {
                                iWebAPIResponse.onFailure(e.message!!)
                            }
                            override fun onSuccess(t: LoginDataModel) {
                                iWebAPIResponse.onSuccess(t, APIType.UserLogin)
                                Log.e("result", t.result[0].access_token)
                                Log.e("result", t.result[0].token_type)
                            }
                        })
                )
            }

            APIType.tounamentsList -> {
                mCompositeDisposable.add(
                    NetworkHandler.getInstance().create()
                        .getTournamentsList("Bearer $accessToken")
                        .subscribeOn(Schedulers.io()).observeOn(
                            AndroidSchedulers.mainThread()
                        ).subscribeWith(object : DisposableSingleObserver<Any>() {
                            override fun onSuccess(t: Any) {
                                iWebAPIResponse.onSuccess(t, APIType.tounamentsList)
                            }

                            override fun onError(e: Throwable) {
                                iWebAPIResponse.onFailure(e.message!!)
                            }
                        })
                )
            }
        }
    }

    private fun getTest(objects: JSONObject): RequestBody {
        val mediaType = MediaType.parse("application/json")
        val body = RequestBody.create(mediaType, objects.toString())
        return body
    }
}
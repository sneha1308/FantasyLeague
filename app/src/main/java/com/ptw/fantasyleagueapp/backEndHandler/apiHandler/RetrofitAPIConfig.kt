package com.ptw.fantasyleagueapp.backEndHandler.apiHandler

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitAPIConfig {
    companion object {
        @Volatile
        private var instance: RetrofitAPIConfig? = null

        fun getInstance() = instance
            ?: synchronized(this) {
            instance
                ?: RetrofitAPIConfig().also { instance = it }
        }
    }
        private lateinit var retrofit: Retrofit
        private val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BaseURL)
        private fun <S> create(serviceClass: Class<S>): S {
            retrofit = builder.build()
            return retrofit.create(serviceClass)
        }

        fun create(): API {
            return create(API::class.java)
        }

}
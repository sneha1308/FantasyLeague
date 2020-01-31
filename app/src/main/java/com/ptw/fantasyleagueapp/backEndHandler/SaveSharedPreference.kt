package com.ptw.fantasyleagueapp.backEndHandler

import android.content.Context
import com.google.gson.Gson
import com.ptw.fantasyleagueapp.FantasyLeagueApplication

class SaveSharedPreference {
    private val FantasyLeagueSP = "FantasyLeagueSP"
    private val FantasyLeagueClientTokenLogin = "FantasyLeagueClientTokenLogin"

    companion object {
        var sessionData: SaveSharedPreference? = SaveSharedPreference()
        fun getSessionDataInstance(): SaveSharedPreference {
            if (sessionData == null) {
                sessionData = SaveSharedPreference()
            }
            return sessionData!!
        }
    }


    fun saveClientTokenData(clientData: String?) {
        val e =
            FantasyLeagueApplication.getInstance().getSharedPreferences(FantasyLeagueSP, Context.MODE_PRIVATE).edit()
        if (clientData != null) {
            e.putString(FantasyLeagueClientTokenLogin, clientData)
        } else {
            e.putString(FantasyLeagueClientTokenLogin, null)
        }
        e.commit()
    }

    fun getClientTokenData(): String? {
        if (FantasyLeagueApplication.getInstance().getSharedPreferences(
                FantasyLeagueSP,
                Context.MODE_PRIVATE
            ).getString(
                FantasyLeagueClientTokenLogin,
                null
            ) == null
        ) {
            return null
        } else {
            val data =
                FantasyLeagueApplication.getInstance().getSharedPreferences(FantasyLeagueSP, Context.MODE_PRIVATE)
                    .getString(FantasyLeagueClientTokenLogin, null)
            return data
        }
    }


    /*
     JSONObject -> Custom class Object (Data Model) below code is used
      val data = FantasyLeagueApplication.getInstance().getSharedPreferences(FantasyLeagueSP, Context.MODE_PRIVATE)
                .getString(TaxClientInfo, null)
            val gson = Gson()
            val obj = gson.fromJson<DataModel>(data, ClientInfoDataModel::class.java!!)

            */


    fun clearAll() {
        val editor =
            FantasyLeagueApplication.getInstance().getSharedPreferences(FantasyLeagueSP, Context.MODE_PRIVATE).edit()
        editor.clear().commit()
        editor.apply()
    }
}
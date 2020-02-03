package com.ptw.fantasyleagueapp

import android.app.Application
class FantasyLeagueApplication : Application() {
    companion object {
        @Volatile
        private var instance: FantasyLeagueApplication? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FantasyLeagueApplication().also { instance = it }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}
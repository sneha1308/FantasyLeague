package com.ptw.fantasyleagueapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ptw.fantasyleagueapp.R


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3 * 1000.toLong())
                    val i = Intent(baseContext, MainActivity::class.java)
                    startActivity(i)
                    finish()
                } catch (e: Exception) {
                }
            }
        }
        background.start()
    }
}

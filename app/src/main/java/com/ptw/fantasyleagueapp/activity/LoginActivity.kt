package com.ptw.fantasyleagueapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ptw.fantasyleagueapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        layFacebookLogin.setOnClickListener {
            openAbout()
        }
        layGoogle.setOnClickListener {
            openAbout()
        }
    }

    private fun openAbout() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}

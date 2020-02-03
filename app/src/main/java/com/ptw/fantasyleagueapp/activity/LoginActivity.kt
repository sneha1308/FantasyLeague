package com.ptw.fantasyleagueapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.GraphRequest
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.ptw.fantasyleagueapp.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import java.util.*


class LoginActivity : AppCompatActivity() {
    val callbackManager = CallbackManager.Factory.create();
    val TAG = LoginActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        layFacebookLogin.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("email", "public_profile"))
            LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult?> {
                    override fun onSuccess(loginResult: LoginResult?) { // App code
                        Log.e(TAG, "${loginResult.toString()}")
                    }

                    override fun onCancel() { // App code
                        Log.e(TAG, "onCancel")
                    }

                    override fun onError(exception: FacebookException) { // App code
                        Log.e(TAG, "Exception $exception \n\n")
                        exception.printStackTrace()

                    }
                })
        }
        layGoogle.setOnClickListener {
            openAbout()
        }
    }

    private fun openAbout() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getUserProfile(currentAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            currentAccessToken
        ) { `object`, response ->
            Log.d("TAG", `object`.toString())
            try {
                openAbout()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email,id")
        request.parameters = parameters
        request.executeAsync()
    }
}

package com.ptw.fantasyleagueapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.ptw.fantasyleagueapp.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val callbackManager = CallbackManager.Factory.create();
    private val TAG = LoginActivity::class.java.name
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("963494167490-pn80ioe46dirnelsa66np7c3h5ngi277.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


//        val account = GoogleSignIn.getLastSignedInAccount(this)
        layFacebookLogin.setOnClickListener(this)
        layGoogle.setOnClickListener(this)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.e(TAG, "Gmail Token ${account!!.idToken}")
            openAbout()
        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

    private fun openAbout() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    /*This method is called to access the facebook token and do the operations based on the status */
    private fun registerThroughLoginManager() {
        //val accessToken = AccessToken.getCurrentAccessToken()
        //   val isLoggedIn = accessToken != null && !accessToken.isExpired

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.e(TAG, "Facebook Token ${loginResult!!.accessToken.token}")
                }

                override fun onCancel() {
                    Log.e(TAG, "onCancel")
                }

                override fun onError(exception: FacebookException) {
                    Log.e(TAG, "Exception $exception \n\n")
                    exception.printStackTrace()
                }
            })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.layFacebookLogin -> {
                LoginManager.getInstance()
                    .logInWithReadPermissions(this, Arrays.asList("email", "public_profile"))
                LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"))
                registerThroughLoginManager()
            }

            R.id.layGoogle -> {
                signIn();
//            openAbout()
            }
        }


    }

}

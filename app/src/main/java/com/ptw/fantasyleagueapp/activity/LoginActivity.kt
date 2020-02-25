package com.ptw.fantasyleagueapp.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.ptw.fantasyleagueapp.R
import com.ptw.fantasyleagueapp.backEndHandler.apiHandler.APIType
import com.ptw.fantasyleagueapp.backEndHandler.apiHandler.FantasyLeagueExecutor
import com.ptw.fantasyleagueapp.backEndHandler.interfaceClass.IWebAPIResponse
import com.ptw.fantasyleagueapp.dataModel.LoginDataModel
import com.squareup.okhttp.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener,
    IWebAPIResponse {

    private val TAG = LoginActivity::class.java.name
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleAPISignInClient: GoogleApiClient
    private lateinit var fantasyLeagueExecutor: FantasyLeagueExecutor

    private val RC_SIGN_IN = 100
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fantasyLeagueExecutor = FantasyLeagueExecutor(this)

        // firebaseAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(resources.getString(R.string.web_application_client_id))
            .requestEmail()
            .build()
        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mGoogleAPISignInClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        mGoogleAPISignInClient.connect()

        callbackManager = CallbackManager.Factory.create();


        try {
            val info =
                packageManager.getPackageInfo("com.ptw.fantasyleagueapp", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val sign: String = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                Log.e("MY KEY HASH:", sign)
                Toast.makeText(applicationContext, sign, Toast.LENGTH_LONG).show()
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
        layFacebookLogin.setOnClickListener(this)
        layGoogle.setOnClickListener(this)
    }

    private fun signIn() {
        if (mGoogleAPISignInClient.isConnected)
            Auth.GoogleSignInApi.signOut(mGoogleAPISignInClient);
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleAPISignInClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    private fun openAbout() {
        startActivity(Intent(this@LoginActivity, TourActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            /* val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
             handleSignInResult(task)*/
            val result: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                //loader.show()
                try {
                    val account = result.getSignInAccount()
                    if (account != null) {
                        val client = OkHttpClient()
                        val requestBody = FormEncodingBuilder()
                            .add("grant_type", "authorization_code")
                            .add(
                                "client_id",
                                resources.getString(R.string.web_application_client_id)
                            )
                            .add("client_secret", resources.getString(R.string.web_application_client_secret))
                            .add("redirect_uri", "")
                            .add("code", account.serverAuthCode)
                            .build();
                        val request = Request.Builder()
                            .url("https://www.googleapis.com/oauth2/v4/token")
                            .post(requestBody)
                            .build();
                        client.newCall(request).enqueue(object : Callback {

                            override fun onFailure(request: Request, e: IOException) {
                                // (applicationContext as SignInSignUpActivity).loader.dismiss()
                                //showMsg(applicationContext, "Something went wrong, please try again later", Toast.LENGTH_LONG)
                            }

                            override fun onResponse(response: Response) {
                                try {
                                    val jsonObject = JSONObject(response.body().string());
                                    val access_token = jsonObject.getString("access_token")
                                    Log.e(TAG, "Gmail Token $access_token")
                                    firebaseAuthWithGoogle(access_token)
                                } catch (e: JSONException) {
                                    // (applicationContext as SignInSignUpActivity).loader.dismiss()
                                    e.printStackTrace()
                                }
                            }
                        })
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                    /*loader.dismiss()
                    showMsg(this, "Google sign in failed", Toast.LENGTH_SHORT)*/
                }
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun firebaseAuthWithGoogle(acct: String?) {
        /* val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
         val token = acct.idToken*/
        if (acct != null) {
            // startActivity(Intent(this, TourActivity::class.java))
            acct.let { it1 -> fantasyLeagueExecutor.callAPI(APIType.UserLogin, it1, "google") }
        }

    }

    /*This method is called to access the facebook token and do the operations based on the status */
    private fun registerThroughLoginManager() {
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        //  val accessToken = AccessToken.getCurrentAccessToken()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {


                    Log.e(TAG, "Facebook Token ${loginResult!!.accessToken.token}")
                    facebookSuccess()
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

    /*This method is called when the callback is success and
    send the access token to server and navigates to MainActivity(Dashboard screen)*/
    fun facebookSuccess() {
        //loader.show()
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if (isLoggedIn) {
            accessToken?.let { it1 -> fantasyLeagueExecutor.callAPI(APIType.UserLogin, it1.token, "facebook") }
        } else {
            Toast.makeText(this, "Facebook login failed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.layFacebookLogin -> {
                val accessToken = AccessToken.getCurrentAccessToken()
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                if (!isLoggedIn) {
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"))
                    registerThroughLoginManager()
                } else {
                    Log.e(TAG, "Facebook Token Success ${accessToken.token}")
                    startActivity(Intent(this, TourActivity::class.java))
                }
            }
            R.id.layGoogle -> {
                signIn();
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this, "Google Login Failed!", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(response: Any, type: APIType) {
        when (type) {
            APIType.UserLogin -> {
                val data = response as LoginDataModel?
                val result = data?.result
                Log.e("loginResult", result.toString())
                startActivity(Intent(this, TourActivity::class.java))
            }
            else -> {
                Toast.makeText(this, "Network response failed", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onFailure(errorTxt: String) {
        Toast.makeText(this, errorTxt, Toast.LENGTH_SHORT).show()
    }

}

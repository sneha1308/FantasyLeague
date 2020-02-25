package com.ptw.fantasyleagueapp.activity

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ptw.fantasyleagueapp.R


class SplashScreenActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds
    lateinit var loader: Dialog

    internal val mRunnable: Runnable = Runnable {

        if (!isFinishing) {
            if (!isNetworkAvailable(this)) {
                // openNoNetwork(R.layout.dialog_no_network)
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
            } else {
                // val token = PreferenceManager.getInstance(this).token
                /*  val intent: Intent?
                  if (token.isNullOrEmpty()) {
                      intent = Intent(applicationContext, LoginActivity::class.java)
                      intent.putExtra("checkPermission", true)
                  } else*/
                intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        //   mDelayHandler?.removeCallbacksAndMessages(null)
    }

    private fun isNetworkAvailable(context: Activity): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onRestart() {
        super.onRestart()
        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish()
        startActivity(intent)
    }

    //opens dialog box when there is no network
/*
    private fun openNoNetwork(layout: Int){
        val noNetworkDialog = customDialog(layout, this)
        val tvRetryButton = noNetworkDialog.findViewById<TextView>(R.id.tvRetry)
        tvRetryButton.setOnClickListener {
            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
            noNetworkDialog.dismiss()
        }
        noNetworkDialog.show()
    }
*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //    loader = getAlertDialog(this)
        // loader.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {
/*
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }*/
        super.onDestroy()
    }
}

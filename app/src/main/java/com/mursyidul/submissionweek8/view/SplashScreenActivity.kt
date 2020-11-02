package com.mursyidul.submissionweek8.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mursyidul.submissionweek8.R
import com.mursyidul.submissionweek8.helper.SessionManager
import com.mursyidul.submissionweek8.view.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000 //3 seconds
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val session = SessionManager(this)
        if (session.login ?: true) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        } else {
            Handler().postDelayed({

                startActivity(Intent(this, MainActivity::class.java))

                finish()
            }, 2000)
        }
    }
}
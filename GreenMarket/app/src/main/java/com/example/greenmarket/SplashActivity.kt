package com.example.greenmarket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.ui.internet.NoInternetActivity
import com.example.greenmarket.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val internetTest = InternetTest()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val intentNoInternet = Intent(this, NoInternetActivity::class.java)
            if (internetTest.isInternetAvailable(this)) {
                startActivity(intent)
            }
            else {
                startActivity(intentNoInternet)
            }
            finish()
        }, 3000)
    }
}
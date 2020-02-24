package com.mirea.ikbo0218.sorokin.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (savedInstanceState == null) {
            val th = Thread {
                Thread.sleep(2000)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            th.start()
        }
    }
}

package com.sessionsesh.lab5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sessionsesh.lab5.cats_activities.RandomCatsActivity

class SplashActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, RandomCatsActivity::class.java))
        Thread{
            Thread.sleep(500)//TODO(make screen loading time equal to data loading)
        }.run()
        finish()
    }
}
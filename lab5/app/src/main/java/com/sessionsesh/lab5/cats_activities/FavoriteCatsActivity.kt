package com.sessionsesh.lab5.cats_activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sessionsesh.lab5.R
import com.sessionsesh.lab5.RecViewAdapter

class FavoriteCatsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_cats_activity)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
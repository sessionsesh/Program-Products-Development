package com.sessionsesh.lab5.cats_activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sessionsesh.lab5.R

class SearchCatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_cats_activity)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
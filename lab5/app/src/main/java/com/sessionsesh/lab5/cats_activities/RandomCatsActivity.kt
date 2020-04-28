package com.sessionsesh.lab5.cats_activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.sessionsesh.lab5.R

class RandomCatsActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set up toolbar
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //TODO(need to think about activity selecting)
        val id = item.itemId
        if (id == R.id.random_cats_item && this.javaClass.simpleName != "RandomCatsActivity") {

            startActivity(Intent(this, this::class.java))
            Toast.makeText(this, this.javaClass.simpleName, Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.search_cats_item) {
            startActivity(Intent(this, SearchCatsActivity::class.java))
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.favorite_cats_item) {
            startActivity(Intent(this, FavoriteCatsActivity::class.java))
            Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

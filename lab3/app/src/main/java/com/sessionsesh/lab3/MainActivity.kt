package com.sessionsesh.lab3

import android.R.attr.button
import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entryButton = findViewById<Button>(R.id.button_new_entry)
        entryButton.setOnClickListener(View.OnClickListener {
            val dialog = NewEntryDialog()
            dialog.show(supportFragmentManager, "MY_TEST")
        })

        val showButton = findViewById<Button>(R.id.button_show_all)
        showButton.setOnClickListener(View.OnClickListener {

            showAllEntries()
        })

    }


    private fun showAllEntries() {
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            StudentsContract.StudentEntry.ID,
            StudentsContract.StudentEntry.COLUMN_FIRST_NAME,
            StudentsContract.StudentEntry.COLUMN_LAST_NAME,
            StudentsContract.StudentEntry.COLUMN_DATE_ADDED
        )

        val cursor = db.query(
            StudentsContract.StudentEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToFirst() // moving from -1 position to the first one
        var data: String = ""
        do {
            val ID = cursor.getInt(cursor.getColumnIndexOrThrow(StudentsContract.StudentEntry.ID))
            val firstName =
                cursor.getString(cursor.getColumnIndexOrThrow(StudentsContract.StudentEntry.COLUMN_FIRST_NAME))
            val lastName =
                cursor.getString(cursor.getColumnIndexOrThrow(StudentsContract.StudentEntry.COLUMN_LAST_NAME))
            val dateAdded =
                cursor.getString(cursor.getColumnIndexOrThrow(StudentsContract.StudentEntry.COLUMN_DATE_ADDED))
            data += "ID: $ID\n" +
                    "firstName: $firstName\n" +
                    "lastName: $lastName\n" +
                    "dateAdded: $dateAdded\n\n"
        } while (cursor.moveToNext())



        Log.d("MyLog", "DATABASE DATA\n\n$data")
    }

}

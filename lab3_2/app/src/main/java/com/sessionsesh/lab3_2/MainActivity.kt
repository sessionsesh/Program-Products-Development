package com.sessionsesh.lab3_2

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val initDb: DBHelper = DBHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = initDb.writableDatabase

        val entryButton = findViewById<Button>(R.id.button_new_entry)
        entryButton.setOnClickListener(View.OnClickListener {
            val dialog = NewEntryDialog()
            dialog.show(supportFragmentManager, "MY_TEST")
        })

        val showButton = findViewById<Button>(R.id.button_show_all)
        showButton.setOnClickListener(View.OnClickListener {
            val i = Intent(this, ListActivity::class.java)
            startActivity(i)

        })

        val clearButton = findViewById<Button>(R.id.button_clear)
        clearButton.setOnClickListener(View.OnClickListener {
            clearEntries()
        })

        val changeButton = findViewById<Button>(R.id.button_change_last)
        changeButton.setOnClickListener(View.OnClickListener {
            changeLastEntry()
        })

    }

    private fun clearEntries() {
        val db = initDb.writableDatabase
        db.execSQL("DELETE FROM ${StudentsContract.StudentEntry.TABLE_NAME}")
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '${StudentsContract.StudentEntry.TABLE_NAME}'")
    }

    private fun changeLastEntry(){
        val db = initDb.writableDatabase
        val cursor = db.rawQuery(
            "SELECT MAX(${StudentsContract.StudentEntry.ID}) as maxID FROM ${StudentsContract.StudentEntry.TABLE_NAME}",
            null
        )
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndex("maxID"))
        cursor.close()

        val values = ContentValues()
        val selection = "${StudentsContract.StudentEntry.ID} = ?"
        values.apply{
            put(StudentsContract.StudentEntry.COLUMN_FIRST_NAME, "Иван")
            put(StudentsContract.StudentEntry.COLUMN_LAST_NAME, "Иванов")
            put(StudentsContract.StudentEntry.COLUMN_MIDDLE_NAME, "Иванович")
        }

        db.update(StudentsContract.StudentEntry.TABLE_NAME, values, selection, arrayOf(id.toString()))
    }
}

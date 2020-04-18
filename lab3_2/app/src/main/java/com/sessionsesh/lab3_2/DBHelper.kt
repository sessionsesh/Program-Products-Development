package com.sessionsesh.lab3_2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${StudentsContract.StudentEntry.TABLE_NAME} (" +
            "${StudentsContract.StudentEntry.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${StudentsContract.StudentEntry.COLUMN_FIRST_NAME} TEXT," +
            "${StudentsContract.StudentEntry.COLUMN_LAST_NAME} TEXT," +
            "${StudentsContract.StudentEntry.COLUMN_MIDDLE_NAME} TEXT," +
            "${StudentsContract.StudentEntry.COLUMN_DATE_ADDED} DATETIME DEFAULT CURRENT_TIMESTAMP)"

    private val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${StudentsContract.StudentEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL(SQL_DELETE_ENTRIES)
        }
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_NAME = "Students.db"
        const val DATABASE_VERSION = 1
    }
}
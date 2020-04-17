package com.sessionsesh.lab3

import android.provider.BaseColumns

class StudentsContract {
    object StudentEntry : BaseColumns {
        const val TABLE_NAME = "students"

        const val ID = BaseColumns._ID
        const val COLUMN_NAME = "name"
        const val COLUMN_DATE_ADDED = "date_added"
    }
}
package com.sessionsesh.lab3

import android.provider.BaseColumns

class StudentsContract {
    object StudentEntry : BaseColumns {
        const val TABLE_NAME = "students"

        const val ID = BaseColumns._ID
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_DATE_ADDED = "date_added"
    }
}
package com.example.lab9_3

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "members"
            val COLUMN_USER_ID = "id"
            val COLUMN_NAME = "name"
            val COLUMN_DATE = "date"
            val COLUMN_DOLZ = "dolz"
            val COLUMN_PHONE = "phone"
        }
    }
}
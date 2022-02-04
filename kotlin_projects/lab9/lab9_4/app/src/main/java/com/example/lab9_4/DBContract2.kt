package com.example.lab9_4

import android.provider.BaseColumns

object DBContract2 {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "info"
            val COLUMN_USER_ID = "id"
            val COLUMN_ZAVODS = "zavods"
            val COLUMN_SHOPS = "shop"
        }
    }
}
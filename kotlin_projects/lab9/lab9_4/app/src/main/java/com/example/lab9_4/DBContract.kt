package com.example.lab9_4

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "rajons"
            val NAME = "name"
            val COLUMN_USER_ID = "id"
            val COLUMN_POPULATION = "popultion"
        }
    }
}
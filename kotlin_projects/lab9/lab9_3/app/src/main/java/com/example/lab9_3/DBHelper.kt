package com.example.lab9_3


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        SQL_CREATE_ENTRIES = "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                DBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                DBContract.UserEntry.COLUMN_NAME + " TEXT," +
                DBContract.UserEntry.COLUMN_DATE + " TEXT," +
                DBContract.UserEntry.COLUMN_DOLZ + " TEXT," +
                DBContract.UserEntry.COLUMN_PHONE + " INTEGER)"
        db.execSQL(SQL_CREATE_ENTRIES)


    }



    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: Member): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_USER_ID, user.id)
        values.put(DBContract.UserEntry.COLUMN_NAME, user.name)
        values.put(DBContract.UserEntry.COLUMN_DATE, user.date)
        values.put(DBContract.UserEntry.COLUMN_DOLZ, user.dolz)
        values.put(DBContract.UserEntry.COLUMN_PHONE, user.phone)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(userid: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.UserEntry.COLUMN_USER_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(userid)
        // Issue SQL statement.
        db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }



    @SuppressLint("Range")
    fun readUser(userid: String): ArrayList<Member> {
        val users = ArrayList<Member>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_USER_ID + "='" + userid + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var name: String
        var date: String
        var dolz: String
        var phone: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                date = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DATE))
                dolz = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DOLZ))
                phone = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PHONE))

                users.add(Member(userid, name, date, dolz, phone))
                cursor.moveToNext()
            }
        }
        return users
    }

    @SuppressLint("Range")
    fun readAllUsers(): ArrayList<Member> {
        val users = ArrayList<Member>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var userid: String
        var name: String
        var date: String
        var dolz: String
        var phone: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                date = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DATE))
                dolz = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DOLZ))
                phone = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PHONE))
                users.add(Member(userid, name, date, dolz, phone))
                cursor.moveToNext()
            }
        }
        return users
    }

    @SuppressLint("Range")
    fun readAllUsersOld(): ArrayList<Member2> {
        val users = ArrayList<Member2>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var userid: String
        var name: String
        var date: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                date = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DATE))
                users.add(Member2(userid, name, date))
                cursor.moveToNext()
            }
        }
        return users
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 2
        val DATABASE_NAME = "FeedReader.db"

        private var SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                    DBContract.UserEntry.COLUMN_NAME + " TEXT," +
                    DBContract.UserEntry.COLUMN_DATE + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }

}

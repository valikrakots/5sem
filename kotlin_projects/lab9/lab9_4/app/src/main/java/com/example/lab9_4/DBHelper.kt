package com.example.lab9_4


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES1)
        db.execSQL(SQL_CREATE_ENTRIES2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_CREATE_ENTRIES1)
        db.execSQL(SQL_CREATE_ENTRIES2)

    }



    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser1(user: Rajon): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_USER_ID, user.id)
        values.put(DBContract.UserEntry.NAME, user.name)
        values.put(DBContract.UserEntry.COLUMN_POPULATION, user.population)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

    fun sumShops(): Int{
        val z: String = "SELECT SUM(" + DBContract2.UserEntry.COLUMN_SHOPS + ")" +
        " FROM " + DBContract2.UserEntry.TABLE_NAME +";"
        var db = readableDatabase
        var amount: Int = 0
        val c = db.rawQuery(z, null);
        if(c.moveToFirst())
            amount = c.getInt(0);
        else
            amount = -1;
        c.close();
        return amount
    }

    fun amountZavods(s: String): Int{
        val z: String = "SELECT SUM(" + DBContract2.UserEntry.COLUMN_ZAVODS + ")" +
                " FROM " + DBContract2.UserEntry.TABLE_NAME + " NATURAL JOIN " + DBContract.UserEntry.TABLE_NAME + " WHERE "+ DBContract.UserEntry.NAME + " = \"" + s + "\";"
        var db = readableDatabase
        var amount: Int = 0
        try {
            val c = db.rawQuery(z, null);
            if (c.moveToFirst())
                amount = c.getInt(0);
            else
                amount = -1;
            c.close();
        }
        catch (ex: Exception){}
        return amount
    }



    @Throws(SQLiteConstraintException::class)
    fun insertUser2(user: Info): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract2.UserEntry.COLUMN_USER_ID, user.id)
        values.put(DBContract2.UserEntry.COLUMN_ZAVODS, user.zavods)
        values.put(DBContract2.UserEntry.COLUMN_SHOPS, user.shops)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract2.UserEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser1(userid: String): Boolean {
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

    @Throws(SQLiteConstraintException::class)
    fun deleteUser2(userid: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract2.UserEntry.COLUMN_USER_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(userid)
        // Issue SQL statement.
        db.delete(DBContract2.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }



    /*@SuppressLint("Range")
    fun readUser(userid: String): ArrayList<Rajon> {
        val users = ArrayList<Rajon>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_USER_ID + "='" + userid + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES1)
            return ArrayList()
        }

        var name: String
        var date: String
        var dolz: String
        var phone: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.))
                date = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DATE))
                dolz = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DOLZ))
                phone = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PHONE))

                users.add(Rajon(userid, name, date, dolz, phone))
                cursor.moveToNext()
            }
        }
        return users
    }*/

    @SuppressLint("Range")
    fun readAllUsers1(): ArrayList<Rajon> {
        val users = ArrayList<Rajon>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES1)
            return ArrayList()
        }

        var userid: String
        var name: String
        var pop: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.NAME))
                pop = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_POPULATION))
                users.add(Rajon(userid, name, pop))
                cursor.moveToNext()
            }
        }
        return users
    }

    @SuppressLint("Range")
    fun readAllUsers2(): ArrayList<Info> {
        val users = ArrayList<Info>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract2.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES2)
            return ArrayList()
        }

        var userid: String
        var zavods: Int
        var shops: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getString(cursor.getColumnIndex(DBContract2.UserEntry.COLUMN_USER_ID))
                zavods = cursor.getInt(cursor.getColumnIndex(DBContract2.UserEntry.COLUMN_ZAVODS))
                shops = cursor.getInt(cursor.getColumnIndex(DBContract2.UserEntry.COLUMN_SHOPS))
                users.add(Info(userid, zavods, shops))
                cursor.moveToNext()
            }
        }
        return users
    }


    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private var SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                    DBContract.UserEntry.NAME + " TEXT," +
                    DBContract.UserEntry.COLUMN_POPULATION + " INT)"
        private var SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + DBContract2.UserEntry.TABLE_NAME + " (" +
                    DBContract2.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                    DBContract2.UserEntry.COLUMN_ZAVODS + " INT," +
                    DBContract2.UserEntry.COLUMN_SHOPS + " INT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }

}

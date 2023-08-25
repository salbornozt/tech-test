package com.satdev.jetpackcompose.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context, databaseVersion: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, databaseVersion) {


    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    companion object {
        const val DATABASE_NAME = "MY_DB.db"

    }
}
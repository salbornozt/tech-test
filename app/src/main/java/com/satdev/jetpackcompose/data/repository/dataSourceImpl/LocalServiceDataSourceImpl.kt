package com.satdev.jetpackcompose.data.repository.dataSourceImpl

import com.satdev.jetpackcompose.data.database.DatabaseHelper
import com.satdev.jetpackcompose.data.repository.dataSource.LocalServiceDataSource
import com.satdev.jetpackcompose.domain.model.SchemaModel
import javax.inject.Inject

class LocalServiceDataSourceImpl @Inject constructor(private val databaseHelper: DatabaseHelper) :
    LocalServiceDataSource {
    override suspend fun initSchema(list: List<SchemaModel>) {
        list.forEach {
            databaseHelper.writableDatabase.execSQL("DROP TABLE IF EXISTS ${it.nombreTabla}")
        }
        list.forEach {
            databaseHelper.writableDatabase.execSQL(it.query)
        }
    }

    override suspend fun getTablesNames(): List<String?> {
        val cursor = databaseHelper.readableDatabase.rawQuery(
            "SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%' AND name NOT LIKE 'android_metadata'",
            arrayOf()
        )
        val list = arrayOfNulls<String>(cursor.count)
        var index = 0
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                list[index] = name
                index++
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list.toList()
    }

}
package com.satdev.jetpackcompose.data.repository.dataSource

import com.satdev.jetpackcompose.domain.model.SchemaModel

interface LocalServiceDataSource {
    suspend fun initSchema(list: List<SchemaModel>)

    suspend fun getTablesNames():List<String?>
}
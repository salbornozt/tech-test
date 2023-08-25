package com.satdev.jetpackcompose.data.repository

import com.satdev.jetpackcompose.data.repository.dataSource.LocalServiceDataSource
import com.satdev.jetpackcompose.data.repository.dataSource.RemoteServiceDataSource
import com.satdev.jetpackcompose.data.repository.model.ToSchemaModel
import com.satdev.jetpackcompose.domain.model.SchemaModel
import com.satdev.jetpackcompose.domain.repository.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val remoteServiceDataSource: RemoteServiceDataSource,
    private val localServiceDataSource: LocalServiceDataSource
) :
    ServiceRepository {
    override suspend fun getServiceResult(): List<SchemaModel> {
        val list = remoteServiceDataSource.getSchema().map { it.ToSchemaModel() }
        localServiceDataSource.initSchema(list)
        return list
    }

    override suspend fun getSchemaTables(): List<String?> {
        return localServiceDataSource.getTablesNames()
    }
}
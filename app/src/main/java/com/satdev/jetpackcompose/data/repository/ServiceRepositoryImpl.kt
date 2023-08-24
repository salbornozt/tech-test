package com.satdev.jetpackcompose.data.repository

import com.satdev.jetpackcompose.data.repository.dataSource.RemoteServiceDataSource
import com.satdev.jetpackcompose.data.repository.model.ToSchemaModel
import com.satdev.jetpackcompose.domain.model.SchemaModel
import com.satdev.jetpackcompose.domain.repository.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(private val remoteServiceDataSource: RemoteServiceDataSource) :
    ServiceRepository {
    override suspend fun getServiceResult(): List<SchemaModel> {
        return remoteServiceDataSource.getSchema().map { it.ToSchemaModel() }
    }
}
package com.satdev.jetpackcompose.data.repository.dataSourceImpl

import com.satdev.jetpackcompose.data.api.ApiService
import com.satdev.jetpackcompose.data.repository.dataSource.RemoteServiceDataSource
import com.satdev.jetpackcompose.data.repository.model.RemoteServiceResponse
import javax.inject.Inject

class RemoteServiceDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteServiceDataSource {
    override suspend fun getSchema(): List<RemoteServiceResponse> {
        return apiService.getScheme()
    }
}
package com.satdev.jetpackcompose.data.repository.dataSource

import com.satdev.jetpackcompose.data.repository.model.RemoteServiceResponse

interface RemoteServiceDataSource {
    suspend fun getSchema(): List<RemoteServiceResponse>
}
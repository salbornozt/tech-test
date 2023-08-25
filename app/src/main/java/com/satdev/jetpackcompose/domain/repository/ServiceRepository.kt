package com.satdev.jetpackcompose.domain.repository

import com.satdev.jetpackcompose.domain.model.SchemaModel

interface ServiceRepository {
    suspend fun getServiceResult() : List<SchemaModel>

    suspend fun getSchemaTables() : List<String?>
}
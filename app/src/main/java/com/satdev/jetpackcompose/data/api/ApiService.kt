package com.satdev.jetpackcompose.data.api

import com.satdev.jetpackcompose.data.repository.model.RemoteServiceResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("usuario: admin")
    @GET("true")
    suspend fun getScheme():List<RemoteServiceResponse>

}
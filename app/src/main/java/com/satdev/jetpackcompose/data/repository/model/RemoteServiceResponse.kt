package com.satdev.jetpackcompose.data.repository.model

import com.google.gson.annotations.SerializedName
import com.satdev.jetpackcompose.domain.model.SchemaModel

data class RemoteServiceResponse(
    @SerializedName("NombreTabla") val nombreTabla: String,
    @SerializedName("Pk") val pk: String,
    @SerializedName("QueryCreacion") val queryCreacion: String,
    @SerializedName("BatchSize") val batchSize: Int,
    @SerializedName("Filtro") val filtro: String,
    @SerializedName("Error") val error: Any,
    @SerializedName("NumeroCampos") val numeroCampos: Int,
    @SerializedName("MetodoApp") val metodoApp: Any,
    @SerializedName("FechaActualizacionSincro") val fechaActualizacionSincro: String
)

fun RemoteServiceResponse.ToSchemaModel(): SchemaModel {
    return SchemaModel(
        nombreTabla = nombreTabla,
        query = queryCreacion
    )
}

package com.satdev.jetpackcompose.data.repository

import com.google.common.truth.Truth.assertThat
import com.satdev.jetpackcompose.data.repository.dataSource.LocalServiceDataSource
import com.satdev.jetpackcompose.data.repository.dataSource.RemoteServiceDataSource
import com.satdev.jetpackcompose.data.repository.model.RemoteServiceResponse
import com.satdev.jetpackcompose.domain.model.SchemaModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ServiceRepositoryImplTest {

    private lateinit var SUT: ServiceRepositoryImpl
    private lateinit var remoteServiceDataSource: RemoteServiceDataSource
    private lateinit var localServiceDataSource: LocalServiceDataSource

    companion object {
        val REMOTE_LIST = listOf<RemoteServiceResponse>(
            RemoteServiceResponse(
                nombreTabla = "",
                pk = "",
                queryCreacion = "",
                batchSize = 1,
                filtro = "",
                error = "",
                numeroCampos = 0,
                metodoApp = "",
                fechaActualizacionSincro = ""
            )
        )
    }

    @Before
    fun setUp() {
        remoteServiceDataSource = mockk()
        localServiceDataSource = mockk()
        SUT = ServiceRepositoryImpl(remoteServiceDataSource, localServiceDataSource)
    }

    @Test
    fun getServiceResult() = runTest {
        //Arrange
        coEvery { remoteServiceDataSource.getSchema() } returns REMOTE_LIST
        coEvery { localServiceDataSource.initSchema(REMOTE_LIST.map { SchemaModel(nombreTabla = it.nombreTabla, query = it.queryCreacion) }) } just runs
        //Act
        val result = SUT.getServiceResult()

        //Assert
        assertThat(result.size).isEqualTo(1)
        coVerify(exactly = 1) {
            remoteServiceDataSource.getSchema()
        }
        coVerify(exactly = 1) {
            localServiceDataSource.initSchema(REMOTE_LIST.map { SchemaModel(nombreTabla = it.nombreTabla, query = it.queryCreacion) })
        }
    }

}
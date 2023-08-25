package com.satdev.jetpackcompose.di

import com.satdev.jetpackcompose.data.api.ApiService
import com.satdev.jetpackcompose.data.database.DatabaseHelper
import com.satdev.jetpackcompose.data.repository.ServiceRepositoryImpl
import com.satdev.jetpackcompose.data.repository.dataSource.LocalServiceDataSource
import com.satdev.jetpackcompose.data.repository.dataSource.RemoteServiceDataSource
import com.satdev.jetpackcompose.data.repository.dataSourceImpl.LocalServiceDataSourceImpl
import com.satdev.jetpackcompose.data.repository.dataSourceImpl.RemoteServiceDataSourceImpl
import com.satdev.jetpackcompose.domain.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Singleton
    @Provides
    fun providesRemoteServiceDataSource(apiService: ApiService): RemoteServiceDataSource {
        return RemoteServiceDataSourceImpl(apiService)
    }
    @Singleton
    @Provides
    fun providesLocalServiceDataSource(databaseHelper: DatabaseHelper): LocalServiceDataSource {
        return LocalServiceDataSourceImpl(databaseHelper)
    }
    @Singleton
    @Provides
    fun providesServiceRepository(remoteServiceDataSource: RemoteServiceDataSource, localServiceDataSource: LocalServiceDataSource): ServiceRepository {
        return ServiceRepositoryImpl(remoteServiceDataSource,localServiceDataSource)
    }
}
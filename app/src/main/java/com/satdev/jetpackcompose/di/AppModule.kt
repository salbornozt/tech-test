package com.satdev.jetpackcompose.di

import android.content.Context
import com.satdev.jetpackcompose.data.api.ApiService
import com.satdev.jetpackcompose.data.database.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(retrofitClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apitesting.interrapidisimo.co/FtAppAgencias012/apiControllerPruebas/api/SincronizadorDatos/ObtenerEsquema/")
            .client(retrofitClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesDatabaseHelper(@ApplicationContext context: Context): DatabaseHelper {
        return DatabaseHelper(context, 2)
    }
}
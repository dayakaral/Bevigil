package com.example.bevigil.di.module

import com.example.bevigil.BuildConfig
import com.example.bevigil.network.AllAssetManager
import com.example.bevigil.network.AllAssetManagerImpl
import com.example.bevigil.network.AllAssetService
import com.example.bevigil.repository.DataRepository
import com.example.bevigil.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideAllAssetService(retrofit: Retrofit) = retrofit.create(AllAssetService::class.java)

    @Provides
    @Singleton
    fun provideAllAssetManager(allAssetManagerImpl: AllAssetManagerImpl): AllAssetManager = allAssetManagerImpl

    @Provides
    @Singleton
    fun provideDataRepository(allAssetManager: AllAssetManager) = DataRepository(allAssetManager)

}
package com.example.countriesappcompose.di

import com.example.countriesappcompose.Constants
import com.example.countriesappcompose.data.remote.api.CountryApi
import com.example.countriesappcompose.data.repository.CountryRepositoryImpl
import com.example.countriesappcompose.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryApi(okhttp : OkHttpClient) : CountryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp)
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkhttpInterceptor() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryRepository(api : CountryApi) : CountryRepository {
        return CountryRepositoryImpl(api)
    }
}
package com.example.countriesappcompose.data.remote.api

import com.example.countriesappxml.data.remote.dto.CountryDto
import retrofit2.http.GET

interface CountryApi {
    @GET("v3.1/all")
    suspend fun getCountries() : List<CountryDto>
}
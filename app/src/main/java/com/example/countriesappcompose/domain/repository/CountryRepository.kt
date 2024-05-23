package com.example.countriesappcompose.domain.repository

import com.example.countriesappcompose.domain.model.Country
import com.example.countriesappxml.data.remote.dto.CountryDto

interface CountryRepository {
    suspend fun getCountries(): List<Country>
}
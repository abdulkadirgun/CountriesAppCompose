package com.example.countriesappcompose.data.repository

import com.example.countriesappcompose.data.remote.api.CountryApi
import com.example.countriesappcompose.domain.model.Country
import com.example.countriesappcompose.domain.repository.CountryRepository
import com.example.countriesappxml.data.remote.dto.CountryDto
import com.example.countriesappxml.data.remote.dto.toCountry
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : CountryRepository {
    override suspend fun getCountries(): List<Country> {
        return api.getCountries().map { it.toCountry() }
    }
}
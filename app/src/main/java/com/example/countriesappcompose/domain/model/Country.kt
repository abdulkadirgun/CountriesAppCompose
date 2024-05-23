package com.example.countriesappcompose.domain.model

import com.example.countriesappxml.data.remote.dto.Name

data class Country(
    val name: String,
    val capital: String?,
    val flag: String
)

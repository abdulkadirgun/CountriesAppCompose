package com.example.countriesappxml.data.remote.dto

import com.example.countriesappcompose.domain.model.Country

data class CountryDto(
    val capital: List<String>?,
    val continents: List<String>,
    val flags: Flags,
    val name: Name,
    val region: String,
    val subregion: String,
    val unMember: Boolean
)
fun CountryDto.toCountry(): Country {
    return Country(
        name = name.official,
        capital = capital?.first(),
        flag = flags.png
    )
}

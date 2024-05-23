package com.example.countriesappcompose.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesappcompose.domain.model.Country
import com.example.countriesappcompose.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CountryRepository
): ViewModel(){

    private var _countries = MutableStateFlow<List<Country>?>(null)
    val countries = _countries.asStateFlow()


    init {
        getCountries()
    }

    private fun getCountries(){
        viewModelScope.launch {
            val result = repository.getCountries()
            _countries.update { result }
            Log.d("MainViewModel", "getCountries: $result")
        }
    }


}
package com.example.countriesappcompose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.countriesappcompose.domain.model.Country
import com.example.countriesappcompose.domain.repository.CountryRepository
import com.example.countriesappcompose.presentation.theme.CountriesAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesAppComposeTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val countries by remember { viewModel.countries }.collectAsState()

    LazyColumn {
        items(countries?.size ?: 0) { index ->
            val country = countries?.get(index)
            if (country != null) {
                CountryCard(
                    imageRes = country.flag,
                    countryName = country.name,
                    countryCapital = country.capital ?: "No Capital"
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val mockCountries = listOf(
        Country(name = "Country A", capital = "Capital A", flag = "https://example.com/flag_a.png"),
        Country(name = "Country B", capital = "Capital B", flag = "https://example.com/flag_b.png"),
        Country(name = "Country C", capital = null, flag = "https://example.com/flag_c.png")
    )

    LazyColumn {
        items(mockCountries.size) { index ->
            val country = mockCountries[index]
            CountryCard(
                imageRes = country.flag,
                countryName = country.name,
                countryCapital = country.capital ?: "No Capital"
            )
        }
    }

}

@Composable
fun CountryCard(imageRes: String, countryName: String, countryCapital: String) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Start
        ) {
            AsyncImage(
                model = imageRes, contentDescription = "Country Flag",
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
            )
            Column {
                Text(text = countryName, style = MaterialTheme.typography.bodyLarge)
                Text(text = countryCapital, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
package com.journey.bangkit.ui.screen.home.jobseeker

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.journey.bangkit.data.source.JourneyDataSource
import com.journey.bangkit.data.source.VacancyDataSource
import com.journey.bangkit.ui.component.home.jobseeker.JSearch
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.viewmodel.HomeJobSeekerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.ui.common.UiState
import com.journey.bangkit.ui.common.ViewModelFactory
import com.journey.bangkit.ui.component.CardSkeleton
import com.journey.bangkit.ui.component.shimmerEffect

@Composable
fun HomeJobSeekerScreen(
    viewModel: HomeJobSeekerViewModel = viewModel(factory = ViewModelFactory())
) {
    val histories = listOf("Software Engineer", "Web Developer")
    var isLoading by remember { mutableStateOf(true) }
    var data by remember { mutableStateOf(listOf<Vacancy>()) }

    viewModel.vacancies.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.getVacancies()
                }, 2000)
            }
            is UiState.Success -> {
                data = uiState.data
                isLoading = false
            }
            is UiState.Error -> isLoading = false
        }

        HomeJobSeekerContent(
            vacancies = data,
            histories = histories,
            isLoading = isLoading
        )
    }
}

@Composable
fun HomeJobSeekerContent(
    modifier: Modifier = Modifier,
    vacancies: List<Vacancy>,
    histories: List<String>,
    isLoading: Boolean
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    var categorySelected by remember { mutableIntStateOf(0) }
    val categories = JourneyDataSource.navigationCategory

    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Blue40)
        ) {
            JSearch(
                query = searchQuery, active = isSearchActive, history = histories,
                setQuery = { searchQuery = it }, setActive = { isSearchActive = it }
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Blue40)
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEachIndexed { index, category ->
                    TextButton(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (index == categorySelected) Color.White else Color.White.copy(.2f)
                        ),
                        onClick = { categorySelected = index },
                    ) {
                        Text(
                            text = category,
                            color = if (index == categorySelected) Blue40 else Color.White
                        )
                    }
                }
            }
        }

        if (isLoading) {
            repeat(5) {
                CardSkeleton(brush = shimmerEffect())
            }
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(vacancies, key = { it.id }) {
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                        ) {
                            Text(text = it.position)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeJobSeekerPreview() {
    JourneyTheme {
        HomeJobSeekerContent(
            vacancies = VacancyDataSource.vacanciesDummy(),
            histories = listOf(),
            isLoading = false
        )
    }
}
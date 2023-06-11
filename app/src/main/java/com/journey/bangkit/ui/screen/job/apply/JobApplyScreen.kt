package com.journey.bangkit.ui.screen.job.apply

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.data.model.VacancyResponse
import com.journey.bangkit.ui.common.UiState
import com.journey.bangkit.ui.common.ViewModelFactory
import com.journey.bangkit.ui.component.CardSkeleton
import com.journey.bangkit.ui.component.shimmerEffect
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.ui.theme.Light
import com.journey.bangkit.viewmodel.JobApplyViewModel

@Composable
fun JobApplyScreen(
    viewModel: JobApplyViewModel = viewModel(factory = ViewModelFactory())
) {
    var isLoading by remember { mutableStateOf(false) }
    var data by remember { mutableStateOf(listOf<VacancyResponse>()) }

    viewModel.vacancies.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.getJobApply()
                }, 1500)
            }
            is UiState.Success -> {
                isLoading = false
                data = uiState.data
            }
            is UiState.Error -> {
                isLoading = false
                Log.d("JobApplyOnProcessScreen", uiState.errorMessage)
            }
        }
    }

    JobApplyContent(data = data, isLoading = isLoading)
}

@Composable
fun JobApplyContent(
    modifier: Modifier = Modifier,
    data: List<VacancyResponse>,
    isLoading: Boolean
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (isLoading) {
            repeat(3) {
                CardSkeleton(brush = shimmerEffect())
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(data[0].vacancies, key = { it.id }) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        colors = CardDefaults.cardColors(containerColor = Light),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(text = it.placement_address)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JobApplyPreview() {
    JourneyTheme {
        JobApplyScreen()
    }
}
package com.journey.bangkit.ui.screen.home.jobprovider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.journey.bangkit.R
import com.journey.bangkit.data.model.Vacancy
import com.journey.bangkit.data.model.VacancyResponse
import com.journey.bangkit.data.source.JourneyDataSource.jobTypes
import com.journey.bangkit.ui.common.UiState
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.Light
import com.journey.bangkit.viewmodel.HomeJobProviderViewModel
import com.journey.bangkit.ui.component.CardSkeleton
import com.journey.bangkit.ui.component.JCardVacancy
import com.journey.bangkit.ui.component.shimmerEffect
import com.journey.bangkit.utils.toDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJobProviderScreen(
    viewModel: HomeJobProviderViewModel = hiltViewModel(),
    navigateToAdd: () -> Unit
) {
    var loading by remember { mutableStateOf(false) }
    var data by remember { mutableStateOf(listOf<Vacancy>()) }

    viewModel.response.collectAsState(initial = UiState.Loading).value.let { res ->
        when (res) {
            is UiState.Loading -> {
                loading = true
                viewModel.getJobCompany()
            }
            is UiState.Success -> {
                loading = false
                data = res.data.vacancies
            }
            is UiState.Error -> loading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.job_vacancy))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue40,
                    titleContentColor = Light
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAdd() },
                containerColor = Blue40,
                contentColor = Light
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_vacancy)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (data.isEmpty() && loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    repeat(5) {
                        CardSkeleton(brush = shimmerEffect())
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(data, key = { it.id }) { vacancy ->
                        JCardVacancy(
                            position = vacancy.placement_address,
                            jobType = jobTypes[vacancy.job_type],
                            skill_one = vacancy.skill_one_name,
                            skill_two = vacancy.skill_two_name,
                            disability = vacancy.disability_name,
                            description = vacancy.description,
                            closedAt = vacancy.deadline_time.toDate()
                        )
                    }
                }
            }
        }
    }
}
package com.journey.bangkit.ui.screen.home.jobprovider

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.journey.bangkit.R
import com.journey.bangkit.data.source.JourneyDataSource
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.ui.theme.Light
import com.journey.bangkit.viewmodel.HomeJobProviderViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.data.model.VacancyResponse
import com.journey.bangkit.data.source.VacancyDataSource
import com.journey.bangkit.ui.common.UiState
import com.journey.bangkit.ui.common.ViewModelFactory
import com.journey.bangkit.ui.component.CardSkeleton
import com.journey.bangkit.ui.component.shimmerEffect

@Composable
fun HomeJobProviderScreen(
    viewModel: HomeJobProviderViewModel = viewModel(factory = ViewModelFactory()),
    navigateToAdd: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var data by remember { mutableStateOf(listOf<VacancyResponse>()) }

    viewModel.vacancies.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.getVacancies()
                }, 1500)
            }
            is UiState.Success -> {
                isLoading = false
                data = uiState.data
            }
            is UiState.Error -> {
                isLoading = false
            }
        }
    }

    HomeJobProviderContent(
        isLoading = isLoading,
        data = data,
        navigateToAdd = navigateToAdd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJobProviderContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    data: List<VacancyResponse>,
    navigateToAdd: () -> Unit
) {
    val menus = JourneyDataSource.menus
    var expandMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.job_vacancy))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue40,
                    titleContentColor = Light
                ),
                actions = {
                    IconButton(onClick = { expandMenu = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(id = R.string.menu),
                            tint = Light
                        )
                    }
                    DropdownMenu(
                        modifier = modifier
                            .width(150.dp)
                            .background(color = Light),
                        expanded = expandMenu,
                        onDismissRequest = { expandMenu = false },
                        properties = PopupProperties()
                    ) {
                        menus.forEach { menu ->
                            DropdownMenuItem(
                                onClick = { 
                                    expandMenu = false
                                }
                            ) {
                                Text(text = stringResource(id = menu))
                            }
                        }
                    }
                }
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
        if (isLoading) {
            Column(
                modifier = modifier.padding(paddingValues)
            ) {
                repeat(5) {
                    CardSkeleton(brush = shimmerEffect())
                }
            }
        } else {
            LazyColumn(
                modifier = modifier.padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(data[0].vacancies, key = { it.id }) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
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
private fun HomeJobProviderPreview() {
    JourneyTheme {
        HomeJobProviderContent(
            isLoading = false,
            data = listOf(VacancyDataSource.vacanciesDummy()),
            navigateToAdd = {}
        )
    }
}
package com.journey.bangkit.ui.screen.home.jobseeker

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.journey.bangkit.data.source.JourneyDataSource
import com.journey.bangkit.ui.component.home.jobseeker.JSearch
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.viewmodel.HomeJobSeekerViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.journey.bangkit.ui.component.CardSkeleton
import com.journey.bangkit.ui.component.JCard
import com.journey.bangkit.ui.component.shimmerEffect
import com.journey.bangkit.utils.toDate
import kotlinx.coroutines.launch

@Composable
fun HomeJobSeekerScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeJobSeekerViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val histories = listOf("Software Engineer", "Web Developer")
    val jobTypes = JourneyDataSource.jobTypes
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    var categorySelected by remember { mutableIntStateOf(1) }
    val categories = JourneyDataSource.navigationCategory
    val context = LocalContext.current
    val vacancies = viewModel.vacancies.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    LaunchedEffect(categorySelected) {
        Toast.makeText(context, categorySelected.toString(), Toast.LENGTH_SHORT).show()
    }

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
                            containerColor = if (index + 1 == categorySelected) Color.White else Color.White.copy(.2f)
                        ),
                        onClick = {
                            categorySelected = index + 1
                            scope.launch {
                                viewModel.setVacancyCategory(index + 1)
                            }
                        },
                    ) {
                        Text(
                            text = stringResource(id = category),
                            color = if (index + 1 == categorySelected) Blue40 else Color.White
                        )
                    }
                }
            }
        }

        if (vacancies.loadState.refresh == LoadState.Loading) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(5) {
                    CardSkeleton(brush = shimmerEffect())
                }
            }
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(count = vacancies.itemCount) { index ->
                    val vacancy = vacancies[index]
                    if (vacancy != null) {
                        JCard(
                            title = vacancy.placement_address,
                            imageUrl = vacancy.company_logo,
                            jobType = jobTypes[vacancy.job_type - 1],
                            disabilityType = vacancy.disability_name,
                            skill_one = vacancy.skill_one_name,
                            skill_two = vacancy.skill_two_name,
                            deadline = vacancy.deadline_time.toDate(),
                            description = vacancy.description,
                            setClick = navigateToDetail,
                            id = vacancy.id
                        )
                    }
                }

                item {
                    if (vacancies.loadState.append is LoadState.Loading) {
                        repeat(5) {
                            CardSkeleton(brush = shimmerEffect())
                        }
                    }
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun HomeJobSeekerPreview() {
    JourneyTheme {
        HomeJobSeekerScreen(
            navigateToDetail = {}
        )
    }
}
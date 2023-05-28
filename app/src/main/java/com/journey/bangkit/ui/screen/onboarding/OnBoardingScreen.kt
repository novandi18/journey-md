package com.journey.bangkit.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.journey.bangkit.R
import com.journey.bangkit.data.source.OnBoardingDataSource
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.JourneyTheme
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.ui.common.ViewModelFactory
import com.journey.bangkit.ui.component.onboarding.OnBoardingContent
import com.journey.bangkit.viewmodel.OnBoardingViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigateToStarted: () -> Unit,
    viewModel: OnBoardingViewModel = viewModel(factory = ViewModelFactory())
) {
    val data = OnBoardingDataSource.data
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(pageCount = data.size, state = pagerState) { pageIndex ->
            OnBoardingContent(
                modifier = modifier,
                data = data,
                pageIndex = pageIndex
            )
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
                .height(90.dp)
                .padding(horizontal = 16.dp)
        ) {
            TextButton(
                onClick = {
                    viewModel.saveOnBoardingState(isCompleted = true, context = context)
                    navigateToStarted()
                },
                modifier = modifier.align(alignment = Alignment.CenterStart)
            ) {
                Text(
                    text = stringResource(id = R.string.skip_onboarding),
                    color = MaterialTheme.colorScheme.onPrimary.copy(0.6f),
                    fontSize = 16.sp
                )
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = data.size,
                modifier = modifier.align(alignment = Alignment.Center),
                activeColor = Blue40
            )
            IconButton(
                onClick = {
                    if (pagerState.currentPage < data.size - 1) {
                        val nextPageIndex = pagerState.currentPage + 1
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(nextPageIndex)
                        }
                    } else {
                        viewModel.saveOnBoardingState(isCompleted = true, context = context)
                        navigateToStarted()
                    }
                },
                modifier = modifier
                    .align(alignment = Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = if (pagerState.currentPage == data.size - 1)
                        Icons.Outlined.Check else Icons.Outlined.ArrowForward,
                    contentDescription = null,
                    tint = Blue40,
                    modifier = modifier.size(32.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    JourneyTheme {
        OnBoardingScreen(
            navigateToStarted = {}
        )
    }
}
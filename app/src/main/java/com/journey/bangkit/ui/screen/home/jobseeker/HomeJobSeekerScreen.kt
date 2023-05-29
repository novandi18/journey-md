package com.journey.bangkit.ui.screen.home.jobseeker

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun HomeJobSeekerScreen() {
    HomeJobSeekerContent()
}

@Composable
fun HomeJobSeekerContent(
    modifier: Modifier = Modifier
) {
    Text(text = "HOME SCREEN FOR JOB SEEKER")
}

@Preview(showBackground = true)
@Composable
private fun HomeJobSeekerPreview() {
    JourneyTheme {
        HomeJobSeekerContent()
    }
}
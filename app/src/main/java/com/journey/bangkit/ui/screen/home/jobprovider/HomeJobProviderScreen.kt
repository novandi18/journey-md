package com.journey.bangkit.ui.screen.home.jobprovider

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun HomeJobProviderScreen() {
    HomeJobProviderContent()
}

@Composable
fun HomeJobProviderContent(
    modifier: Modifier = Modifier
) {
    Text(text = "HOME SCREEN FOR JOB PROVIDER")
}

@Preview(showBackground = true)
@Composable
private fun HomeJobProviderPreview() {
    JourneyTheme {
        HomeJobProviderContent()
    }
}
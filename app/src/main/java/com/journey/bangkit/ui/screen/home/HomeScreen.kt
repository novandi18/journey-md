package com.journey.bangkit.ui.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "Home")
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JourneyTheme {
        HomeScreen()
    }
}
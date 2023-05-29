package com.journey.bangkit.ui.screen.profile.jobprovider

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun ProfileJobProviderScreen() {
    ProfileJobProviderContent()
}

@Composable
fun ProfileJobProviderContent(
    modifier: Modifier = Modifier
) {
    Text(text = "PROFILE SCREEN FOR JOB PROVIDER")
}

@Preview(showBackground = true)
@Composable
private fun ProfileJobProviderPreview() {
    JourneyTheme {
        ProfileJobProviderContent()
    }
}
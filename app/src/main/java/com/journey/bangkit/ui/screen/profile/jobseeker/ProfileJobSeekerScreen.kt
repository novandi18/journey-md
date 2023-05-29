package com.journey.bangkit.ui.screen.profile.jobseeker

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun ProfileJobSeekerScreen() {
    ProfileJobSeekerContent()
}

@Composable
fun ProfileJobSeekerContent(
    modifier: Modifier = Modifier
) {
    Text(text = "PROFILE SCREEN FOR JOB SEEKER")
}

@Preview(showBackground = true)
@Composable
private fun ProfileJobSeekerPreview() {
    JourneyTheme {
        ProfileJobSeekerContent()
    }
}
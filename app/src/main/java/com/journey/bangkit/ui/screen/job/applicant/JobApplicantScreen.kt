package com.journey.bangkit.ui.screen.job.applicant

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun JobApplicantScreen() {
    JobApplicantContent()
}

@Composable
fun JobApplicantContent(
    modifier: Modifier = Modifier
) {
    Text(text = "JOB APPLICANT SCREEN FOR JOB PROVIDER")
}

@Preview(showBackground = true)
@Composable
private fun JobApplicantPreview() {
    JourneyTheme {
        JobApplicantContent()
    }
}
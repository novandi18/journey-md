package com.journey.bangkit.ui.screen.job.apply

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun JobApplyScreen() {
    JobApplyContent()
}

@Composable
fun JobApplyContent(
    modifier: Modifier = Modifier
) {
    Text(text = "JOB APPLY SCREEN FOR JOB SEEKER")
}

@Preview(showBackground = true)
@Composable
private fun JobApplyPreview() {
    JourneyTheme {
        JobApplyContent()
    }
}
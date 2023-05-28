package com.journey.bangkit.ui.component.started

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.journey.bangkit.R
import com.journey.bangkit.ui.theme.Blue80

@Composable
fun BottomButton(
    modifier: Modifier,
    navigateToJobSeeker: () -> Unit,
    navigateToJobProvider: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.auth_description),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(bottom = 56.dp)
        ) {
            Button(
                onClick = { navigateToJobSeeker() },
                modifier = modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.auth_jobseeker),
                    color = Blue80,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = { navigateToJobProvider() },
                modifier = modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.auth_jobprovider),
                    color = Blue80,
                    fontSize = 18.sp
                )
            }
        }
    }
}
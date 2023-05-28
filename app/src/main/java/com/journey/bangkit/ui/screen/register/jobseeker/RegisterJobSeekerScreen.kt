package com.journey.bangkit.ui.screen.register.jobseeker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.journey.bangkit.R
import com.journey.bangkit.ui.component.auth.AuthHeader
import com.journey.bangkit.ui.component.auth.AuthTitle
import com.journey.bangkit.ui.component.auth.register.jobseeker.RegisterJobSeekerForm
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.viewmodel.RegisterJobSeekerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.data.source.DisabilityDataSource
import com.journey.bangkit.ui.common.UiState
import com.journey.bangkit.ui.common.ViewModelFactory

@Composable
fun RegisterJobSeekerScreen(
    backToLogin: () -> Unit,
    viewModel: RegisterJobSeekerViewModel = viewModel(factory = ViewModelFactory())
) {
    viewModel.disability.collectAsState(initial = UiState.Loading).value.let { uiState ->
        var isLoading by remember { mutableStateOf(false) }
        var disabilityData by mutableStateOf(listOf<Disability>())

        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                viewModel.getDisabilities()
            }
            is UiState.Error -> isLoading = false
            is UiState.Success -> {
                val disability = uiState.data
                disabilityData = disability
                isLoading = false
            }
        }

        RegisterJobSeekerContent(
            backToLogin = backToLogin,
            disability = disabilityData,
            isLoading = isLoading
        )
    }
}

@Composable
fun RegisterJobSeekerContent(
    modifier: Modifier = Modifier,
    backToLogin: () -> Unit,
    disability: List<Disability>,
    isLoading: Boolean = false
) {
    Column {
        AuthHeader(back = backToLogin, title = stringResource(id = R.string.jobseeker))
        if (isLoading) {
            LinearProgressIndicator(
                modifier = modifier.fillMaxWidth(),
                color = Blue40
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AuthTitle(
                title = stringResource(id = R.string.register_title),
                description = stringResource(id = R.string.register_description)
            )
            RegisterJobSeekerForm(
                disabilityData = disability
            )
            Box(
                modifier = modifier.padding(vertical = 56.dp, horizontal = 24.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = modifier
                        .background(color = Blue40, shape = CircleShape)
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_register),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterJobSeekerPreview() {
    JourneyTheme {
        RegisterJobSeekerContent(
            backToLogin = {},
            disability = DisabilityDataSource.disabilities
        )
    }
}
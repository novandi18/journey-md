package com.journey.bangkit.ui.screen.login.jobseeker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.R
import com.journey.bangkit.ui.component.auth.AuthHeader
import com.journey.bangkit.ui.component.auth.AuthTitle
import com.journey.bangkit.ui.component.auth.login.LoginBottom
import com.journey.bangkit.ui.component.auth.login.LoginForm
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun LoginJobSeekerScreen(
    modifier: Modifier = Modifier,
    backToStarted: () -> Unit,
    navigateToRegister: () -> Unit
) {
    Column {
        AuthHeader(
            back = backToStarted,
            title = stringResource(id = R.string.jobseeker)
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            AuthTitle(
                title = stringResource(id = R.string.login_title),
                description = stringResource(id = R.string.login_description)
            )
            LoginForm()
            LoginBottom(
                navigateToRegister = navigateToRegister
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginJobSeekerPreview() {
    JourneyTheme {
        LoginJobSeekerScreen(
            backToStarted = {},
            navigateToRegister = {}
        )
    }
}
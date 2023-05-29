package com.journey.bangkit.ui.screen.login.jobseeker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.R
import com.journey.bangkit.ui.component.auth.AuthHeader
import com.journey.bangkit.ui.component.auth.AuthTitle
import com.journey.bangkit.ui.component.auth.login.LoginBottom
import com.journey.bangkit.ui.component.auth.login.LoginForm
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.viewmodel.LoginJobSeekerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.ui.common.ViewModelFactory

@Composable
fun LoginJobSeekerScreen(
    backToStarted: () -> Unit,
    navigateToRegister: () -> Unit,
    viewModel: LoginJobSeekerViewModel = viewModel(factory = ViewModelFactory())
) {
    val context = LocalContext.current
    LoginJobSeekerContent(
        backToStarted = backToStarted,
        navigateToRegister = navigateToRegister,
        doLogin = {
            viewModel.saveTokenLogin(context)
        }
    )
}

@Composable
fun LoginJobSeekerContent(
    modifier: Modifier = Modifier,
    backToStarted: () -> Unit,
    navigateToRegister: () -> Unit,
    doLogin: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
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
            LoginForm(
                doLogin = doLogin
            )
            LoginBottom(
                navigateToRegister = navigateToRegister
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginJobSeekerPreview() {
    JourneyTheme {
        LoginJobSeekerContent(
            backToStarted = {},
            navigateToRegister = {},
            doLogin = {}
        )
    }
}
package com.journey.bangkit.ui.screen.register.jobprovider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.journey.bangkit.R
import com.journey.bangkit.ui.component.auth.AuthHeader
import com.journey.bangkit.ui.component.auth.AuthTitle
import com.journey.bangkit.ui.component.auth.register.jobprovider.RegisterJobProviderForm
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.JourneyTheme
import com.journey.bangkit.viewmodel.RegisterJobProviderViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journey.bangkit.data.model.City
import com.journey.bangkit.data.model.Province
import com.journey.bangkit.data.model.Sector
import com.journey.bangkit.ui.common.UiState
import com.journey.bangkit.ui.common.ViewModelFactory

@Composable
fun RegisterJobProviderScreen(
    backToLogin: () -> Unit,
    viewModel: RegisterJobProviderViewModel = viewModel(factory = ViewModelFactory())
) {
    val context = LocalContext.current
    viewModel.region.collectAsState(initial = UiState.Loading).value.let { uiState ->
        var isLoading by rememberSaveable { mutableStateOf(false) }
        var provinceSelected by rememberSaveable { mutableStateOf(11) }
        var provinceData by mutableStateOf(listOf<Province>())
        var cityData by mutableStateOf(listOf<City>())
        var sectorData by mutableStateOf(listOf<Sector>())

        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                viewModel.getRegion(context)
            }
            is UiState.Error -> isLoading = false
            is UiState.Success -> {
                val data = uiState.data
                provinceData = data.first
                cityData = data.second
                sectorData = data.third
                isLoading = false
            }
        }

        RegisterJobProviderContent(
            backToLogin = backToLogin,
            isLoading = isLoading,
            province = provinceData,
            city = cityData,
            sector = sectorData,
            onProvinceChange = {
                if (provinceSelected != provinceData[it].id) {
                    provinceSelected = provinceData[it].id
                    viewModel.getRegion(context, provinceSelected)
                }
            }
        )
    }
}

@Composable
fun RegisterJobProviderContent(
    modifier: Modifier = Modifier,
    backToLogin: () -> Unit,
    isLoading: Boolean = false,
    province: List<Province>,
    city: List<City>,
    sector: List<Sector>,
    onProvinceChange: (Int) -> Unit
) {
    Column {
        AuthHeader(back = backToLogin, title = stringResource(id = R.string.jobprovider))
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
            RegisterJobProviderForm(
                province = province,
                city = city,
                sector = sector,
                onProvinceChange = onProvinceChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterJobProviderPreview() {
    JourneyTheme {
        RegisterJobProviderContent(
            backToLogin = {},
            province = listOf(),
            city = listOf(),
            sector = listOf(),
            onProvinceChange = {}
        )
    }
}

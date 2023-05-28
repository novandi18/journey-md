package com.journey.bangkit.ui.component.auth.register.jobseeker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistWalker
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.journey.bangkit.R
import com.journey.bangkit.data.model.Disability
import com.journey.bangkit.ui.component.JDropdownMenu
import com.journey.bangkit.ui.component.JPasswordField
import com.journey.bangkit.ui.component.JTextField
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun RegisterJobSeekerForm(
    modifier: Modifier = Modifier,
    disabilityData: List<Disability>
) {
    val provinces = disabilityData.map { it.name }

    Column(
        modifier = modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        JTextField(
            modifier = modifier,
            leadingIcon = Icons.Filled.Person,
            label = stringResource(id = R.string.fullname),
            keyboardType = KeyboardType.Text,
            placeholder = stringResource(id = R.string.fullname_placeholder)
        )
        JTextField(
            modifier = modifier,
            leadingIcon = Icons.Filled.Email,
            label = stringResource(id = R.string.email),
            keyboardType = KeyboardType.Email,
            placeholder = stringResource(id = R.string.email_placeholder)
        )
        JTextField(
            modifier = modifier,
            leadingIcon = Icons.Filled.Phone,
            label = stringResource(id = R.string.phone_number),
            keyboardType = KeyboardType.Phone,
            placeholder = stringResource(id = R.string.phonenumber_placeholder)
        )
        JTextField(
            modifier = modifier,
            leadingIcon = Icons.Filled.Home,
            label = stringResource(id = R.string.address),
            keyboardType = KeyboardType.Text,
            placeholder = stringResource(id = R.string.address_placeholder)
        )
        JTextField(
            modifier = modifier,
            leadingIcon = Icons.Filled.Person3,
            label = stringResource(id = R.string.age),
            placeholder = stringResource(id = R.string.age_placeholder),
            keyboardType = KeyboardType.Number
        )
        JDropdownMenu(
            icon = Icons.Filled.AssistWalker,
            label = stringResource(id = R.string.disability_placeholder),
            data = provinces,
            setItemSelected = {}
        )
        JPasswordField()
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterJobSeekerFormPreview() {
    JourneyTheme {
        RegisterJobSeekerForm(
            disabilityData = listOf()
        )
    }
}
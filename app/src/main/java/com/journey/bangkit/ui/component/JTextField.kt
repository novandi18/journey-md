package com.journey.bangkit.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.journey.bangkit.R
import com.journey.bangkit.ui.theme.JourneyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholder: String
) {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        modifier = modifier.fillMaxWidth().height(56.dp),
        singleLine = true,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = text,
            innerTextField = { it() },
            enabled = true,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = {
                Text(text = placeholder)
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = label
                )
            },
            trailingIcon = {
                if (text.isNotBlank()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.clear)
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JTextFieldPreview() {
    JourneyTheme {
        JTextField(
            leadingIcon = Icons.Filled.Email,
            label = "Email",
            keyboardType = KeyboardType.Email,
            placeholder = stringResource(id = R.string.email_placeholder)
        )
    }
}
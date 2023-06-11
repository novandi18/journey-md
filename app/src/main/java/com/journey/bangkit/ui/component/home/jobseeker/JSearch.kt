package com.journey.bangkit.ui.component.home.jobseeker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.journey.bangkit.R
import com.journey.bangkit.ui.theme.Blue40
import com.journey.bangkit.ui.theme.Dark
import com.journey.bangkit.ui.theme.JourneyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JSearch(
    modifier: Modifier = Modifier,
    setQuery: (String) -> Unit,
    setActive: (Boolean) -> Unit,
    query: String,
    active: Boolean,
    history: List<String>
) {
    SearchBar(
        modifier = modifier
            .background(color = if (active) Color.White else Color.Transparent)
            .padding(
                bottom = if (active) 0.dp else 8.dp,
                start = if (active) 0.dp else 8.dp,
                end = if (active) 0.dp else 8.dp
            )
            .fillMaxWidth(),
        query = query,
        onQueryChange = { setQuery(it) },
        onSearch = { setActive(false) },
        active = active,
        onActiveChange = { setActive(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = Blue40
            )
        },
        trailingIcon = {
            if (active) {
                IconButton(onClick = {
                    setActive(false)
                    setQuery("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.cancel)
                    )
                }
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color.White,
            dividerColor = Dark.copy(alpha = .3f)
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                color = Dark.copy(alpha = .5f)
            )
        }
    ) {
        history.map {
            TextButton(
                onClick = {},
                modifier = modifier.fillMaxWidth(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = modifier
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(imageVector = Icons.Filled.History, contentDescription = "", tint = Dark)
                    Text(text = it, color = Dark)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JSearchPreview() {
    JourneyTheme {
        JSearch(setQuery = {}, setActive = {}, query = "", active = false, history = listOf())
    }
}
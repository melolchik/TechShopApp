package ru.melolchik.techshopapp.settings.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.melolchik.techshopapp.settings.R
import ru.melolchik.techshopapp.settings.presentation.component.LanguageDropdown
import ru.melolchik.techshopapp.settings.presentation.component.ThemeDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    paddingValues: PaddingValues
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    Box(Modifier
        .fillMaxSize()
        .padding(paddingValues = paddingValues),
        contentAlignment = Alignment.Center)
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()

        ) {

            Text(
                text = stringResource(R.string.hint_theme),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))

            ThemeDropdown(
                selected = state.theme,
                onSelected = viewModel::onThemeChange
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.hint_language),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))

            LanguageDropdown(
                selected = state.language,
                onSelected = viewModel::onLanguageChange
            )
        }
    }
}
package ru.melolchik.techshopapp.settings.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.melolchik.techshopapp.datastore.components.ThemeParam

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDropdown(
    selected: ThemeParam,
    onSelected: (ThemeParam) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        TextField(
            value = stringResource(selected.nameResId),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            ThemeParam.entries.forEach { theme ->

                DropdownMenuItem(
                    text = {
                        Text(stringResource(theme.nameResId))
                    },
                    onClick = {
                        expanded = false
                        onSelected(theme)
                    }
                )
            }
        }
    }
}
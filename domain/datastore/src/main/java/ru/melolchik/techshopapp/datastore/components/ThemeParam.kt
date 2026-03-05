package ru.melolchik.techshopapp.datastore.components

import androidx.annotation.StringRes
import ru.melolchik.techshopapp.datastore.R

enum class ThemeParam(val value: String, @StringRes val nameResId : Int) {
    LIGHT("light", R.string.theme_light),
    DARK("dark", nameResId = R.string.theme_dark)
}



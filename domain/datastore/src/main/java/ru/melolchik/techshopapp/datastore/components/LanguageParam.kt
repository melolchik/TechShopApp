package ru.melolchik.techshopapp.datastore.components

import androidx.annotation.StringRes
import ru.melolchik.techshopapp.datastore.R

enum class LanguageParam(val value: String, @StringRes val nameResId : Int) {
    RU("ru-RU", R.string.language_ru),
    EN("en-US", nameResId = R.string.language_en)
}

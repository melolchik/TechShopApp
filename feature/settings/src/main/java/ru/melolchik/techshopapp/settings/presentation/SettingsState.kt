package ru.melolchik.techshopapp.settings.presentation

import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam

data class SettingsState(val theme: ThemeParam = ThemeParam.LIGHT,
                         val language: LanguageParam = LanguageParam.RU)


package ru.melolchik.techshopapp.datastore.repository

import kotlinx.coroutines.flow.Flow
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam

interface SettingsRepository {

    val themeFlow: Flow<ThemeParam>
    val languageFlow: Flow<LanguageParam>

    suspend fun setTheme(theme: ThemeParam)
    suspend fun setLanguage(language: LanguageParam)
}
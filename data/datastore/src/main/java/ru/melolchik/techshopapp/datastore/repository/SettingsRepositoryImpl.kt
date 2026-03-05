package ru.melolchik.techshopapp.datastore.repository

import jakarta.inject.Inject
import kotlinx.coroutines.flow.map
import ru.melolchik.techshopapp.datastore.component.SettingsDataStore
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.datastore.mapper.toLanguageParam
import ru.melolchik.techshopapp.datastore.mapper.toThemeParam

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: SettingsDataStore
) : SettingsRepository {

    override val themeFlow = dataStore.themeFlow.map { value ->
        value.toThemeParam()
    }
    override val languageFlow = dataStore.languageFlow.map { value ->
        value.toLanguageParam()
    }

    override suspend fun setTheme(theme: ThemeParam) {
        dataStore.setTheme(theme.value)
    }

    override suspend fun setLanguage(language: LanguageParam) {
        dataStore.setLanguage(language.value)
    }
}
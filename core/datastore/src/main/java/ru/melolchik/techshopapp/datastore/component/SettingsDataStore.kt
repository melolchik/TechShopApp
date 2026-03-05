package ru.melolchik.techshopapp.datastore.component


import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")

class SettingsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private object Keys {
        val THEME = stringPreferencesKey("theme")
        val LANGUAGE = stringPreferencesKey("language")
    }

    val themeFlow: Flow<String> =
        context.dataStore.data.map {
            it[Keys.THEME] ?: ThemeParam.LIGHT.value
        }

    val languageFlow: Flow<String> =
        context.dataStore.data.map {
            it[Keys.LANGUAGE] ?: LanguageParam.RU.value
        }

    suspend fun setTheme(theme: String) {
        context.dataStore.edit {
            it[Keys.THEME] = theme
        }
    }

    suspend fun setLanguage(language: String) {
        context.dataStore.edit {
            it[Keys.LANGUAGE] = language
        }
    }
}
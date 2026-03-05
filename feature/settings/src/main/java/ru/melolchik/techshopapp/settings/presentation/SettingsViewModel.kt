package ru.melolchik.techshopapp.settings.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.datastore.usecase.ChangeLanguageUseCase
import ru.melolchik.techshopapp.datastore.usecase.ChangeThemeUseCase
import ru.melolchik.techshopapp.datastore.usecase.ObserveLanguageUseCase
import ru.melolchik.techshopapp.datastore.usecase.ObserveThemeUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    observeTheme: ObserveThemeUseCase,
    observeLanguage: ObserveLanguageUseCase,
    private val changeTheme: ChangeThemeUseCase,
    private val changeLanguage: ChangeLanguageUseCase
) : ViewModel() {


    val state: StateFlow<SettingsState> =
        combine(
            observeTheme(),
            observeLanguage()
        ) { theme, language ->
            SettingsState(theme, language)
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                SettingsState()
            )

    fun onThemeChange(theme: ThemeParam) {
        viewModelScope.launch {
            changeTheme(theme)

        }
    }

    fun onLanguageChange(language: LanguageParam) {
        viewModelScope.launch {
            changeLanguage(language)
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language.value)
            )
        }
    }
}
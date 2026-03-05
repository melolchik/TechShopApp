package ru.melolchik.techshopapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.datastore.usecase.ObserveLanguageUseCase
import ru.melolchik.techshopapp.datastore.usecase.ObserveThemeUseCase
import ru.melolchik.techshopapp.settings.presentation.SettingsState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    observeTheme: ObserveThemeUseCase,
    observeLanguage: ObserveLanguageUseCase
) : ViewModel(){
    val themeState: StateFlow<ThemeParam> =

        observeTheme().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ThemeParam.LIGHT
        )

    val languageState: StateFlow<LanguageParam> =

        observeLanguage().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            LanguageParam.RU
        )

}
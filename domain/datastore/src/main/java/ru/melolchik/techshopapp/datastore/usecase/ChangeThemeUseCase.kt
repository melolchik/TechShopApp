package ru.melolchik.techshopapp.datastore.usecase

import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.datastore.repository.SettingsRepository
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(theme: ThemeParam) {
        repository.setTheme(theme)
    }
}
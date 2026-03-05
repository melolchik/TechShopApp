package ru.melolchik.techshopapp.datastore.usecase

import kotlinx.coroutines.flow.Flow
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.datastore.repository.SettingsRepository
import javax.inject.Inject


class ObserveThemeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<ThemeParam> =
        repository.themeFlow
}
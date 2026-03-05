package ru.melolchik.techshopapp.datastore.usecase

import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.repository.SettingsRepository
import javax.inject.Inject


class ChangeLanguageUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(lang: LanguageParam) {
        repository.setLanguage(lang)
    }
}
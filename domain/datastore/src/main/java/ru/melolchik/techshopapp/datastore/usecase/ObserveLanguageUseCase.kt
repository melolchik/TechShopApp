package ru.melolchik.techshopapp.datastore.usecase

import kotlinx.coroutines.flow.Flow
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.repository.SettingsRepository
import javax.inject.Inject



class ObserveLanguageUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<LanguageParam> =
        repository.languageFlow
}
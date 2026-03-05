package ru.melolchik.techshopapp.datastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.melolchik.techshopapp.datastore.repository.SettingsRepository
import ru.melolchik.techshopapp.datastore.repository.SettingsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository
}
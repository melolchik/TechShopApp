package ru.melolchik.techshopapp.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.melolchik.techshopapp.products.data.repository.ProductsRepositoryImpl
import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductsRepository(
        impl: ProductsRepositoryImpl
    ): ProductsRepository
}
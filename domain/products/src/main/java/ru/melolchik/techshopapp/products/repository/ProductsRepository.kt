package ru.melolchik.techshopapp.products.repository

import kotlinx.coroutines.flow.Flow
import ru.melolchik.techshopapp.products.model.Product

interface ProductsRepository {
    fun getProducts(): Flow<List<Product>>

    fun getFavorites(): Flow<List<Product>>

    suspend fun getProductById(id: String): Product?

    suspend fun toggleFavorite(product: Product)

    suspend fun syncProducts()
}
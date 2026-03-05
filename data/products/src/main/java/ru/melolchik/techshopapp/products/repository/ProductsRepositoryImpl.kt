package ru.melolchik.techshopapp.products.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.melolchik.techshopapp.database.dao.ProductDao
import ru.melolchik.techshopapp.network.api.ProductApi
import ru.melolchik.techshopapp.products.mapper.toDomain
import ru.melolchik.techshopapp.products.mapper.toEntity
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val dao: ProductDao
) : ProductsRepository {

    override fun getProducts(): Flow<List<Product>> {
        return dao.getAllProducts()
            .map { list -> list.map { it.toDomain() } }
    }

    override fun getFavorites(): Flow<List<Product>> {
        return dao.getFavoriteProducts()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getProductById(id: String): Product? {
        return dao.getProductById(id)?.toDomain()
    }

    override suspend fun toggleFavorite(product: Product) {
         dao.updateFavorite(product.id, !product.isFavorite)
    }

    override suspend fun syncProducts() {
        val remoteProducts = api.getProducts()

        val entities = remoteProducts.map { dto ->
            dto.toEntity()
        }
        Log.d("ProductsRepositoryImpl", "entities = $entities")
        dao.clearAll()
        dao.insertAll(entities)
    }
}
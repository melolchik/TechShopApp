package ru.melolchik.techshopapp.products.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
    }
}
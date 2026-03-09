package ru.melolchik.techshopapp.details.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Inject


class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    operator fun invoke(productId: String) : Flow<Product?> {
        return repository.observeProductById(productId)
    }
}
package ru.melolchik.techshopapp.details.domain.usecase

import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Inject


class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(productId: String) : Product? {
        return repository.getProductById(productId)
    }
}
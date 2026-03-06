package ru.melolchik.techshopapp.products.usecase

import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(product: Product) {
        repository.toggleFavorite(product = product)
    }
}
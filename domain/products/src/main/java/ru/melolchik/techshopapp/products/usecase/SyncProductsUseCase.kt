package ru.melolchik.techshopapp.products.usecase

import ru.melolchik.techshopapp.products.repository.ProductsRepository
import javax.inject.Inject

class SyncProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke() {
        repository.syncProducts()
    }
}
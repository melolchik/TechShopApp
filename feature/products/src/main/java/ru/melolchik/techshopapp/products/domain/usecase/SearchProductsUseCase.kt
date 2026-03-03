package ru.melolchik.techshopapp.products.domain.usecase

import ru.melolchik.techshopapp.products.model.Product
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor() {

    operator fun invoke(
        products: List<Product>,
        query: String
    ): List<Product> {

        if (query.isBlank()) return products

        return products.filter {
            it.model.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }
    }
}
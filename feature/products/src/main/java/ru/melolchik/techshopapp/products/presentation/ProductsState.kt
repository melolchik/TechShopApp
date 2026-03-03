package ru.melolchik.techshopapp.products.presentation

import ru.melolchik.techshopapp.products.model.Product


sealed class ProductsState {

    object Initial : ProductsState()

    object Loading : ProductsState()

    data class Result(val items : List<Product>,
                      val searchQuery: String = "") : ProductsState()
}

package ru.melolchik.techshopapp.favorites.presentation

import ru.melolchik.techshopapp.products.model.Product


sealed class FavoritesState {

    object Initial : FavoritesState()

    object Loading : FavoritesState()

    data class Result(val items : List<Product>) : FavoritesState()
}

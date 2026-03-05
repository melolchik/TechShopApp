package ru.melolchik.techshopapp.details.presentation

import ru.melolchik.techshopapp.products.model.Product

sealed class DetailsState {

    object Initial : DetailsState()

    object Loading : DetailsState()

    data class Result(val product : Product) : DetailsState()

    data class Error(val error : String) : DetailsState()
}

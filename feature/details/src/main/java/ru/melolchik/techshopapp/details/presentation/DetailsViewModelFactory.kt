package ru.melolchik.techshopapp.details.presentation

import dagger.assisted.AssistedFactory


@AssistedFactory
interface DetailsViewModelFactory {
    fun create(productId: String): DetailsViewModel
}
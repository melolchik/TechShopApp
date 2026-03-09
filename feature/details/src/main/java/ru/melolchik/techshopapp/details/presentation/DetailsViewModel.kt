package ru.melolchik.techshopapp.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.melolchik.techshopapp.details.domain.usecase.GetProductByIdUseCase
import ru.melolchik.techshopapp.products.usecase.ToggleFavoriteUseCase

@HiltViewModel(assistedFactory = DetailsViewModelFactory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val productId: String,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {


    val state: StateFlow<DetailsState> = getProductByIdUseCase(productId)
        .map { product ->
            if (product == null) {
                DetailsState.Error(error = "Product not found")
            } else {
                DetailsState.Result(product = product)
            }
        }.stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            DetailsState.Loading)



    fun onFavoriteClick() {
         val currentState = state.value
         if( currentState is DetailsState.Result) {
             val product = currentState.product
             viewModelScope.launch {
                 toggleFavorite(product)
             }
         }
    }
}
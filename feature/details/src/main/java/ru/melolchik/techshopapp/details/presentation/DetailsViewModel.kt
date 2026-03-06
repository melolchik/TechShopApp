package ru.melolchik.techshopapp.details.presentation

import android.util.Log
import ru.melolchik.techshopapp.products.usecase.ToggleFavoriteUseCase
import ru.melolchik.techshopapp.products.model.Product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.melolchik.techshopapp.details.domain.usecase.GetProductByIdUseCase

@HiltViewModel(assistedFactory = DetailsViewModelFactory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val productId: String,
    private val getProductById: GetProductByIdUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState.Initial as DetailsState)
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    init {
        Log.d("DetailsViewModel", "init productId = $productId")
        loadProduct(productId)
    }



    //val state: StateFlow<DetailsState> =

    fun loadProduct(id: String) {
        viewModelScope.launch {
            try {
                _state.value = DetailsState.Loading

                val product = getProductById(id)

                if (product != null) {
                    _state.value = DetailsState.Result(product = product)
                } else {
                    _state.value = DetailsState.Error(
                        error = "Product not found"
                    )
                }
            } catch (e: Exception) {
                _state.value = DetailsState.Error(
                    error = e.message ?: "Error"
                )
            }
        }
    }

    fun onFavoriteClick() {
         val state = _state.value
         if(state is DetailsState.Result) {
             val product = state.product
             viewModelScope.launch {
                 toggleFavorite(product)
                 loadProduct(product.id)
             }
         }
    }
}
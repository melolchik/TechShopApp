package ru.melolchik.techshopapp.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.melolchik.techshopapp.favorites.domain.usecase.GetFavoritesUseCase
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.usecase.ToggleFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<FavoritesState> =
        getFavoritesUseCase()
            .map { products ->
                FavoritesState.Result(
                    items = products
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = FavoritesState.Initial
            )

    fun toggleFavorite(product : Product){
        viewModelScope.launch {
            toggleFavoriteUseCase(product = product)
        }

    }
}
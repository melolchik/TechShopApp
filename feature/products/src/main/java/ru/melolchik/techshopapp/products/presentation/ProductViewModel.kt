package ru.melolchik.techshopapp.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.melolchik.techshopapp.products.domain.usecase.GetProductsUseCase
import ru.melolchik.techshopapp.products.domain.usecase.SearchProductsUseCase
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.usecase.SyncProductsUseCase
import ru.melolchik.techshopapp.products.usecase.ToggleFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val syncProductsUseCase: SyncProductsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    val state: StateFlow<ProductsState> =
        combine(
            getProductsUseCase(),
            searchQuery
        ) { products, query ->

            val filtered = searchProductsUseCase(products, query)

            ProductsState.Result(
                items = filtered,
                searchQuery = query
            )
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                ProductsState.Initial
            )

    fun onSearchChange(query: String) {
        searchQuery.value = query
    }

    fun refresh() {
        viewModelScope.launch {
            syncProductsUseCase()
        }
    }

    fun toggleFavorite(product : Product){
        viewModelScope.launch {
            toggleFavoriteUseCase(product = product)
        }

    }
}
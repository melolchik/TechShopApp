package ru.melolchik.techshopapp.favorites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import ru.melolchik.techshopapp.products.model.Product

@Composable
fun FavoritesScreen(
    paddingValues: PaddingValues,
    onProductClick: (Product) -> Unit

) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    when (state) {
        is FavoritesState.Initial,
        is FavoritesState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is FavoritesState.Result -> {
            val result = state as FavoritesState.Result
            if(result.items.isEmpty()) {
                Box(Modifier.fillMaxSize()) {
                    Text("Нет избранных товаров")
                }
            }

            else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(result.items, key = { it.id }) { product ->
                        FavoriteItem(
                            product = product,
                            onClick = { onProductClick(product) }
                        )
                    }
                }
            }
        }
    }
}
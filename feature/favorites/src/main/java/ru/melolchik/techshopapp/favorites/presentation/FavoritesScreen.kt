package ru.melolchik.techshopapp.favorites.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.melolchik.techshopapp.favorites.R
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.ui.components.EmptyScreen
import ru.melolchik.techshopapp.ui.components.ProductList
import ru.melolchik.techshopapp.ui.components.ProgressScreen

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
            ProgressScreen(paddingValues = paddingValues)
        }

        is FavoritesState.Result -> {
            val result = state as FavoritesState.Result
            if (result.items.isEmpty()) {
                EmptyScreen(
                    paddingValues = paddingValues,
                    text = stringResource(R.string.favorites_list_is_empty)
                )
            } else {
                ProductList(
                    modifier = Modifier
                        .padding(paddingValues = paddingValues)
                        .padding(top = 16.dp),
                    list = result.items,
                    onProductClick,
                    onLikeClick = {
                        viewModel.toggleFavorite(product = it)
                    }
                )
            }
        }
    }
}

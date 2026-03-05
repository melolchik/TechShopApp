package ru.melolchik.techshopapp.favorites.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.melolchik.techshopapp.favorites.R
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.ui.components.EmptyScreen
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
               ItemList(paddingValues = paddingValues,list = result.items ) {onProductClick }
            }
        }
    }
}

@Composable
fun ItemList(paddingValues: PaddingValues,
             list: List<Product>,
             onProductClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {

        items(list, key = { it.id }) { product ->
            FavoriteItem(
                product = product,
                onClick = { onProductClick(product) }
            )
        }
    }
}

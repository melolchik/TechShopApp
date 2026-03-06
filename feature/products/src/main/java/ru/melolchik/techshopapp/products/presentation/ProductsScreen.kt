package ru.melolchik.techshopapp.products.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.melolchik.techshopapp.products.R
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.ui.components.EmptyScreen
import ru.melolchik.techshopapp.ui.components.ProductList
import ru.melolchik.techshopapp.ui.components.ProgressScreen

@Composable
fun ProductsScreen(
    paddingValues: PaddingValues,
    onProductClick: (Product) -> Unit

) {

    val viewModel: ProductsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState(ProductsState.Initial)

    when (state) {
        is ProductsState.Result -> {
            val result = state as ProductsState.Result

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                SearchItem(result.searchQuery, viewModel::onSearchChange)
                if (result.items.isEmpty()) {
                    EmptyScreen(
                        paddingValues = paddingValues,
                        text = stringResource(R.string.products_list_is_empty)
                    )
                } else {
                    ProductList(
                        modifier = Modifier,
                        result.items,
                        onProductClick,
                        onLikeClick = {
                            viewModel.toggleFavorite(product = it)
                        }
                    )
                }
            }
        }

        else -> {
            ProgressScreen(paddingValues = paddingValues)
        }

    }

}

@Composable
fun SearchItem(searchQuery: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text(stringResource(R.string.hint_search)) }
    )
}

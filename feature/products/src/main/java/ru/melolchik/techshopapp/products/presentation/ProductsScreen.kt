package ru.melolchik.techshopapp.products.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.melolchik.techshopapp.products.model.Product

@Composable
fun ProductsScreen(
    paddingValues: PaddingValues,
    onProductClick: (Product) -> Unit

) {

    val viewModel: ProductsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState(ProductsState.Initial)

    Column(modifier =  Modifier.fillMaxSize()
        .padding(paddingValues = paddingValues)) {

        if (state is ProductsState.Result) {
            val result = state as ProductsState.Result
            val search = result.searchQuery
            OutlinedTextField(
                value = search,
                onValueChange = viewModel::onSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Поиск") }
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                items(result.items, key = { it.id }) { product ->
                    ProductItem(
                        product = product,
                        onClick = { onProductClick(product) }
                    )
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }

}
package ru.melolchik.techshopapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.melolchik.techshopapp.products.model.Product

@Composable
fun ProductList(modifier: Modifier,list: List<Product>, onProductClick: (Product) -> Unit,
                onLikeClick: (Product) -> Unit) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(list, key = { it.id }) { product ->
            ProductItem(
                product = product,
                onClick = { onProductClick(product) },
                onLikeClick = { onLikeClick(product) }
            )
        }
    }
}
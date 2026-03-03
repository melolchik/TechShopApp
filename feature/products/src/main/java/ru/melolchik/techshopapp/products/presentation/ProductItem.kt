package ru.melolchik.techshopapp.products.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.melolchik.techshopapp.products.model.Product

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(product.model, style = MaterialTheme.typography.titleMedium)
            Text(product.description, maxLines = 2)
            Text("Цена: ${product.price} ₽")
        }
    }
}
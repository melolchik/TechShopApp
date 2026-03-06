package ru.melolchik.techshopapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.ui.R
import ru.melolchik.techshopapp.ui.theme.TechShopAppTheme

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
    onLikeClick: (Product) -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Row(Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically ) {
                Text(modifier = Modifier.weight(weight = 0.4f),
                    text = product.model,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(weight = 0.5f))
                LikeButton(modifier = Modifier.wrapContentSize()
                    .weight(weight = 0.1f),
                    isFavorite = product.isFavorite) {
                    onLikeClick(product)
                }
            }

            Text(product.description, maxLines = 2)
            Text(stringResource(R.string.tag_price, product.price))
        }
    }
}

@Preview
@Composable
fun ProductItemPreview(){

    TechShopAppTheme {
        ProductItem(product = Product.Test , onClick = {}) { }
    }

}
@Composable
fun LikeButton(
    modifier: Modifier,
    isFavorite: Boolean,
    onClick: () -> Unit
) {

    Box(modifier = modifier
        //.padding(16.dp)
        .clickable(){
        onClick()
    }) {
        Icon(

            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}


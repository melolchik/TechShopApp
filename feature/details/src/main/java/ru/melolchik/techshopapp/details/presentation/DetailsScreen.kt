package ru.melolchik.techshopapp.details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.material3.Icon
import ru.melolchik.techshopapp.ui.components.EmptyScreen
import ru.melolchik.techshopapp.ui.components.ProgressScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    productId: String,
    onBackClick: () -> Unit
) {

    val viewModel: DetailsViewModel = hiltViewModel(
        creationCallback = { factory: DetailsViewModelFactory ->
            factory.create(productId = productId)
        }
    )
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        //Text(text = "back")
                    }
                }
            )
        }
    ) { padding ->

        Text(text = "ProductId = $productId")
        when (state) {
            is DetailsState.Initial,
            DetailsState.Loading -> {
                ProgressScreen(paddingValues = padding)
            }
            is DetailsState.Error -> {
                EmptyScreen(paddingValues = padding,
                    "Ошибка: ${(state as DetailsState.Error).error}")
            }

            is DetailsState.Result -> {
                val product = (state as DetailsState.Result).product

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(product.model, style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(product.description)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Категория: ${product.categories}")
                    Text("Цена: ${product.price} ₽")
                    Text("Обновлено: ${product.editedAt}")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.onFavoriteClick() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (product.isFavorite)
                                "Удалить из избранного"
                            else
                                "Добавить в избранное"
                        )
                    }
                }
            }

        }
    }
}
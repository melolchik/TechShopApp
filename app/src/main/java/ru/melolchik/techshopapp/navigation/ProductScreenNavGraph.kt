package ru.melolchik.techshopapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.melolchik.techshopapp.products.model.Product


fun NavGraphBuilder.productScreenNavGraph(
    productsScreenContent : @Composable () -> Unit,
    detailsScreenContent : @Composable (String) -> Unit
) {
    navigation(
        startDestination = Screen.Products.route,
        route = Screen.Home.route
    ) {
        composable(Screen.Products.route) {
            productsScreenContent()
        }



    }
}
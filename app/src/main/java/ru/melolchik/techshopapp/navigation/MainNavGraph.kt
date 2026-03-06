package ru.melolchik.techshopapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.melolchik.techshopapp.products.model.Product

@Composable
fun MainNavGraph(navHostController: NavHostController,
                 productsScreenContent : @Composable () -> Unit,
                 favoriteScreenContent : @Composable () -> Unit,
                 settingsScreenContent : @Composable ()-> Unit
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Products.route
        ){

        composable(Screen.Products.route) {
            productsScreenContent()
        }

        composable(Screen.Favorite.route){
            favoriteScreenContent()
        }

        composable(Screen.Settings.route){
            settingsScreenContent()
        }
    }

}
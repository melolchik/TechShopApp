package ru.melolchik.techshopapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.melolchik.techshopapp.products.model.Product

@Composable
fun AppNavGraph (
    navHostController: NavHostController,
    splashScreenContent : @Composable () -> Unit,
    mainScreenContent : @Composable () -> Unit
){
    NavHost(
    navController = navHostController,
    startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            splashScreenContent()
        }

        composable(Screen.Home.route) {
            mainScreenContent()
        }


    }
}
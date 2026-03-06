package ru.melolchik.techshopapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    splashScreenContent: @Composable () -> Unit,
    mainScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable (String) -> Unit
) {
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

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(Screen.KEY_PRODUCT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(Screen.KEY_PRODUCT_ID) ?: ""
            detailsScreenContent(productId)
        }
    }
}
package ru.melolchik.techshopapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.melolchik.techshopapp.products.model.Product

class NavigationState(val navHostController: NavHostController) {

    fun navigateToHome(){
        navHostController.navigate(Screen.Home.route){
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    fun navigateTo(route : String){
        navHostController.navigate(route){
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToDetails(product : Product){
        navHostController.navigate(Screen.Details.getRouteWithArgs(product = product))// comments/15/
    }
}

@Composable
fun rememberNavigateState(
    navHostController: NavHostController = rememberNavController()
) : NavigationState{
    return remember {
        NavigationState(navHostController)
    }
}
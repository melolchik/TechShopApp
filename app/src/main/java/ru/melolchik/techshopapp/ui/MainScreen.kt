package ru.melolchik.techshopapp.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.favorites.presentation.FavoritesScreen
import ru.melolchik.techshopapp.navigation.MainNavGraph
import ru.melolchik.techshopapp.navigation.NavigationItem
import ru.melolchik.techshopapp.navigation.NavigationState
import ru.melolchik.techshopapp.navigation.rememberNavigateState
import ru.melolchik.techshopapp.products.model.Product
import ru.melolchik.techshopapp.products.presentation.ProductsScreen
import ru.melolchik.techshopapp.settings.presentation.SettingsScreen
import ru.melolchik.techshopapp.ui.theme.TechShopAppTheme

fun log(text: String) {
    Log.d("COMPOSE_TEST", text)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(onProductClick: (Product) -> Unit,
               onSettingsLanguageChanged: (LanguageParam) -> Unit) {


    val navigationState = rememberNavigateState()


    Scaffold(
        bottomBar = {
            BottomBar(navigationState = navigationState)
        }

    ) { paddingValues ->

        MainNavGraph(
            navHostController = navigationState.navHostController,
            productsScreenContent = {
                ProductsScreen (paddingValues = paddingValues){product ->
                    onProductClick( product)
                }
            },
            favoriteScreenContent = {
                FavoritesScreen(paddingValues = paddingValues) {product ->
                    onProductClick( product)
                }
            },
            settingsScreenContent = {
                SettingsScreen(paddingValues = paddingValues,onSettingsLanguageChanged)
            })
    }
}


@Composable
fun BottomBar(navigationState: NavigationState = rememberNavigateState()){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        val items =
            listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
        val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

        items.forEachIndexed { index, item ->

            val selected = navBackStackEntry?.destination?.hierarchy?.any {
                it.route == item.screen.route
            } ?: false

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if(!selected) {
                        navigationState.navigateTo(item.screen.route)
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = item.titleResId))

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreviewDark(){
    TechShopAppTheme(true) {
        BottomBar()
    }
}

@Preview
@Composable
fun MainScreenPreviewLight(){
    TechShopAppTheme(false) {
        BottomBar()
    }
}
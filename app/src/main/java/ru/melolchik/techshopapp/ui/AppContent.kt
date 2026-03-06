package ru.melolchik.techshopapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.details.presentation.DetailsScreen
import ru.melolchik.techshopapp.navigation.AppNavGraph
import ru.melolchik.techshopapp.navigation.rememberNavigateState
import ru.melolchik.techshopapp.splash.presentation.SplashScreen
import ru.melolchik.techshopapp.ui.theme.TechShopAppTheme


@Composable
fun AppContent(onSettingsLanguageChanged: (LanguageParam) -> Unit) {

    val viewModel: MainViewModel = hiltViewModel()
    val themeParam by viewModel.themeState.collectAsState(ThemeParam.LIGHT)
    TechShopAppTheme(darkTheme = themeParam == ThemeParam.DARK) {


        val navigationState = rememberNavigateState()
        AppNavGraph(
            navHostController = navigationState.navHostController,
            splashScreenContent = {
                SplashScreen {
                    navigationState.navigateToHome()
                }
            },
            mainScreenContent = {
                MainScreen(
                    onProductClick = { product ->
                        navigationState.navigateToDetails(product = product)
                    },
                    onSettingsLanguageChanged = { languageParam ->
                        onSettingsLanguageChanged(languageParam)
                    })
            },
            detailsScreenContent = { productId ->
                DetailsScreen(productId = productId, onBackClick = {
                    navigationState.navHostController.popBackStack()
                })

            })

    }
}
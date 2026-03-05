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

        composable(
            route = Screen.Details.route//,
//           arguments = listOf(
//               navArgument(Screen.KEY_PRODUCT){
//                   type = FeedPost.NavigationType
//               }
//           )
        ) { backStackEntry  ->
//           val product = backStackEntry.arguments?.getParcelable<Product>(Screen.KEY_PRODUCT)
//               ?: throw RuntimeException("Args is null")
            val productId = backStackEntry.arguments?.getString(Screen.KEY_PRODUCT) ?: ""
            detailsScreenContent(productId)
        }

        /*composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(Screen.KEY_PRODUCT){
                    type = FeedPost.NavigationType
                }
            )
        ) { entry ->
            val feedPost = entry.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                ?: throw RuntimeException("Args is null")
            commentsScreenContent(feedPost)
        }*/
    }
}
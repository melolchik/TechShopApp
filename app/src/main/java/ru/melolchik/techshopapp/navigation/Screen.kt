package ru.melolchik.techshopapp.navigation

import android.net.Uri
import com.google.gson.Gson
import ru.melolchik.techshopapp.products.model.Product

sealed class Screen(val route: String) {

    object Splash : Screen(ROUTE_SPLASH)
    object Products : Screen(ROUTE_PRODUCTS)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Settings : Screen(ROUTE_SETTINGS)

    object Home : Screen(ROUTE_HOME)
    object Details : Screen(ROUTE_DETAILS){

        private const val ROUTE_FOR_ARGS = "details"
        fun getRouteWithArgs(product: Product) : String {

            val productJson = Gson().toJson(product)
            return "$ROUTE_FOR_ARGS/${productJson.encode()}"
        }
    }


    companion object {
        const val KEY_PRODUCT = "product"

        const val ROUTE_SPLASH = "splash"
        const val ROUTE_HOME = "home"
        const val ROUTE_DETAILS = "details/{$KEY_PRODUCT}"
        const val ROUTE_PRODUCTS = "products"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_SETTINGS = "settings"

    }
}


fun String.encode() : String {
    return Uri.encode(this)
}
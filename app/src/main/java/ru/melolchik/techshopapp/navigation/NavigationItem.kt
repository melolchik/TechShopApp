package ru.melolchik.techshopapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import ru.melolchik.techshopapp.R


sealed class NavigationItem(
    val screen: Screen,
    //val titleResId : Int,
    val icon: ImageVector
){
    object Home : NavigationItem(
        Screen.Products,
       // ru.melolchik.techshopapp.R.string.navigation_item_main,
        Icons.Outlined.Home
    )
    object Favorite : NavigationItem (
        Screen.Favorite,
        //R.string.navigation_item_fav,
        Icons.Outlined.Favorite
    )
    object Profile : NavigationItem (
        Screen.Settings,
        //R.string.navigation_item_profile,
        Icons.Outlined.Settings
    )
}
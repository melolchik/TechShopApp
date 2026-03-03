package ru.melolchik.techshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.melolchik.techshopapp.navigation.AppNavGraph
import ru.melolchik.techshopapp.navigation.rememberNavigateState
import ru.melolchik.techshopapp.splash.presentation.SplashScreen
import ru.melolchik.techshopapp.ui.MainScreen
import ru.melolchik.techshopapp.ui.theme.TechShopAppTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechShopAppTheme {
                val navigationState = rememberNavigateState()
                AppNavGraph(navHostController = navigationState.navHostController,
                    splashScreenContent = {
                        SplashScreen{
                            navigationState.navigateToHome()
                        }
                    },
                    mainScreenContent = {
                        MainScreen()
                    } ,
                    detailsScreenContent = {
                        Text(text = "Details")
                    })

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()){

    }
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TechShopAppTheme {
        Greeting("Android")
    }
}
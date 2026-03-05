package ru.melolchik.techshopapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam
import ru.melolchik.techshopapp.navigation.AppNavGraph
import ru.melolchik.techshopapp.navigation.rememberNavigateState
import ru.melolchik.techshopapp.splash.presentation.SplashScreen
import ru.melolchik.techshopapp.ui.MainScreen
import ru.melolchik.techshopapp.ui.MainViewModel
import ru.melolchik.techshopapp.ui.theme.TechShopAppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val viewModel: MainViewModel = hiltViewModel()

    val theme = viewModel.themeState.collectAsState(ThemeParam.LIGHT)
    val language = viewModel.languageState.collectAsState(LanguageParam.RU)

    ApplyLanguage(language.value)

    TechShopAppTheme(darkTheme = theme.value == ThemeParam.DARK) {
        val navigationState = rememberNavigateState()
        AppNavGraph(
            navHostController = navigationState.navHostController,
            splashScreenContent = {
                SplashScreen {
                    navigationState.navigateToHome()
                }
            },
            mainScreenContent = {
                MainScreen()
            },
            detailsScreenContent = {
                Text(text = "Details")
            })

    }
}

@Composable
fun ApplyLanguage(language: LanguageParam) {

    Log.d("COMPOSE_TEST", "ApplyLanguage $language")

//    val applicationContext = LocalContext.current
//    var prevLang by rememberSaveable {
//        mutableStateOf("")
//    }

//    LaunchedEffect(key1 = prevLang, key2 = language.value) {
//        prevLang = language.value

    //SideEffect {
//        AppCompatDelegate.setApplicationLocales(
//            LocaleListCompat.forLanguageTags(language.value)
//        )
    //}

    //   }

    // Update the activity's context with the new locale

}

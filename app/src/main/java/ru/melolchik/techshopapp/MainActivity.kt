package ru.melolchik.techshopapp

import android.app.LocaleManager
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
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
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppContent { languageParam ->
                applyLanguage(languageParam)
            }
        }
    }

    fun applyLanguage(language: LanguageParam) {
        val langTag = language.value

//    AppCompatDelegate.setApplicationLocales(
//        LocaleListCompat.forLanguageTags(langTag)//    )

        val localeManager =
            ContextCompat.getSystemService<LocaleManager>(this, LocaleManager::class.java)
        localeManager.applicationLocales = LocaleList(Locale.forLanguageTag(langTag))
    }
}

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
                MainScreen(onSettingsLanguageChanged)
            })

    }
}




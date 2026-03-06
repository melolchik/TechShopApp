package ru.melolchik.techshopapp

import android.app.LocaleManager
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.ui.AppContent
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

        val localeManager =
            ContextCompat.getSystemService<LocaleManager>(this, LocaleManager::class.java)
        localeManager.applicationLocales = LocaleList(Locale.forLanguageTag(langTag))
    }
}





package ru.melolchik.techshopapp.splash.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType.Companion.Text
import androidx.compose.ui.draw.scale
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {

    val viewModel: SplashViewModel = hiltViewModel()

    val isFinished by viewModel.isFinished.collectAsState()

    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        viewModel.startSync()
    }

    if (isFinished) {
        LaunchedEffect(Unit) {
            onFinished()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Garden Store",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.scale(scale)
        )
    }
}
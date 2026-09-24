package ru.melolchik.techshopapp.splash.presentation

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType.Companion.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.melolchik.techshopapp.splash.R

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {

    val viewModel: SplashViewModel = hiltViewModel()

    val isFinished by viewModel.isFinished.collectAsStateWithLifecycle()

    val infiniteTransition = rememberInfiniteTransition(label = "splash")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit) {
        snapshotFlow { scale }
            .collect {
                Log.d("SPLASH", "scale = $it")
            }
    }

    LaunchedEffect(Unit) {
        viewModel.startSync()
    }


    LaunchedEffect(isFinished) {
        if (isFinished) {
            onFinished()
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {




        Log.d("TAG", "SplashScreen: $scale")
        //AnimatedBox(scale)
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
            //.scale(scale)
        )
    }
}

@Composable
fun AnimatedBox(scale: Float) {
    Log.d("TAG", "SplashScreen: $scale")
    Box(
        modifier = Modifier
            .scale(scale)
            .wrapContentSize()
    ) {}
    Text(
//        modifier = Modifier.graphicsLayer(
//            scaleX = scale,
//            scaleY = scale
//        ),
        text = stringResource(R.string.splash_text),
        style = MaterialTheme.typography.headlineLarge,


        )

}
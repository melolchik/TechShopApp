package ru.melolchik.techshopapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.melolchik.techshopapp.ui.theme.TechShopAppTheme

@Composable
fun ProgressScreen(paddingValues: PaddingValues){
    Box(
        Modifier
        .fillMaxSize()
        .padding(paddingValues = paddingValues),
        contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyScreen(paddingValues: PaddingValues, text : String){
    Box(Modifier
        .fillMaxSize()
        .padding(paddingValues = paddingValues),
        contentAlignment = Alignment.Center)
    {
        Text(modifier = Modifier.wrapContentSize()
            .border(
                width = 1.dp,
                brush = SolidColor(MaterialTheme.colorScheme.onSurface),
                shape = RoundedCornerShape(1.dp)
            )
            .padding(16.dp),
            text = text,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun TestEmptyScreen() {

    TechShopAppTheme {
        EmptyScreen(paddingValues = PaddingValues(), text = "Test")
    }
}

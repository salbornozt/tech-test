package com.satdev.jetpackcompose.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Obtener Esquema")
        }
    }

}
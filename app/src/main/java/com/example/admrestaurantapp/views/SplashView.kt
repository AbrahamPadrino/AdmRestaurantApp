package com.example.admrestaurantapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.admrestaurantapp.R
import com.example.admrestaurantapp.navigate.Views
import com.example.admrestaurantapp.utils.Constantes
import kotlinx.coroutines.delay

@Composable
fun SplashView(
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        delay(Constantes.DURACION_SPLASH_SCREEN)
        navController.popBackStack()
        navController.navigate(Views.InicioView.route)
    }

    SplashScreen()
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GlideImage(
            model = R.drawable.restautant,
            contentDescription = "gif_logo"
        )
    }
}
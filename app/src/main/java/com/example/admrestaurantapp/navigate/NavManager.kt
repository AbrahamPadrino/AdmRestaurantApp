package com.example.admrestaurantapp.navigate

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.admrestaurantapp.viewmodels.CategoriaViewModel
import com.example.admrestaurantapp.viewmodels.PlatilloViewModel
import com.example.admrestaurantapp.views.CategoriaView
import com.example.admrestaurantapp.views.InicioView
import com.example.admrestaurantapp.views.PlatilloView
import com.example.admrestaurantapp.views.SplashView

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavManager() {
    val navController = rememberNavController()

    val catViewModel = CategoriaViewModel()
    val plaViewModel = PlatilloViewModel()

    NavHost(
        navController = navController,
        startDestination = Views.SplashView.route
    ) {
        composable(Views.InicioView.route) {
            InicioView(navController = navController)
        }

        composable(Views.SplashView.route) {
            SplashView(navController = navController)
        }

        composable(Views.CategoriaView.route) {
            CategoriaView(
                navController = navController,
                viewModel = catViewModel
            )
        }

        composable(Views.PlatilloView.route) {
            PlatilloView(
                navController = navController,
                viewModel = plaViewModel
            )
        }
    }
}
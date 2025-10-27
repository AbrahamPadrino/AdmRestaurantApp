package com.example.admrestaurantapp.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
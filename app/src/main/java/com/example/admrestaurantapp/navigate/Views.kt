package com.example.admrestaurantapp.navigate

sealed class Views(
    val route: String
) {
    object SplashView: Views("splash_screen")
    object InicioView: Views("inicio")
    object CategoriaView: Views("categoria")
    object PlatilloView: Views("platillo")
}
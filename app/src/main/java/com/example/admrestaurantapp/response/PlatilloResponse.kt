package com.example.admrestaurantapp.response

import com.example.admrestaurantapp.models.Platillo

data class PlatilloResponse(
    val codigo: String,
    val mensaje: String,
    val datos: List<Platillo>
)
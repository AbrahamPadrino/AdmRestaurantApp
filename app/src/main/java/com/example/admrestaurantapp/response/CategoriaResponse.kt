package com.example.admrestaurantapp.response

import com.example.admrestaurantapp.models.Categoria

data class CategoriaResponse(
    val codigo: String,
    val mensaje: String,
    val datos: List<Categoria>
)
package com.example.admrestaurantapp.models

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("nom_categoria")
    var nomCategoria: String,
    @SerializedName("img_categoria")
    var imagenCategoria: String
)
package com.example.admrestaurantapp.models

import com.google.gson.annotations.SerializedName

data class Platillo(
    @SerializedName("nom_platillo")
    var nomPlatillo: String = "",
    @SerializedName("descripcion_platillo")
    var descPlatillo: String = "",
    @SerializedName("precio")
    var precio: Double = 0.0,
    @SerializedName("cal_platillo")
    var calPlatillo: Double = 0.0,
    @SerializedName("nom_categoria")
    var categoria: String = ""
)
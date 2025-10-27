package com.example.admrestaurantapp.network

import com.example.admrestaurantapp.models.Platillo
import com.example.admrestaurantapp.response.CategoriaResponse
import com.example.admrestaurantapp.response.PlatilloResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {

    // CATEGORIAS
    @GET("/categorias")
    suspend fun obtenerCategorias()
    : Response<CategoriaResponse>

    @FormUrlEncoded
    @POST("/categorias/add")
    suspend fun agregarCategoria(
        @Field("nom_categoria") nomCategoria: String
    ): Response<CategoriaResponse>

    @DELETE("/categorias/delete/{nomCategoria}")
    suspend fun borrarCategoria(
        @Path("nomCategoria") nomCategoria: String
    ): Response<CategoriaResponse>
    // END CATEGORIAS

    // PLATILLOS
    @GET("/platillos")
    suspend fun obtenerPlatillos()
    : Response<PlatilloResponse>

    @POST("/platillos/add")
    suspend fun agregarPlatillo(
        @Body platillo: Platillo
    ): Response<PlatilloResponse>

    @PUT("/platillos/update/{nomPlatillo}")
    suspend fun actualizarPlatillo(
        @Path("nomPlatillo") nomPlatillo: String,
        @Body platillo: Platillo
    ): Response<PlatilloResponse>

    @DELETE("/platillos/delete/{nomPlatillo}")
    suspend fun borrarPlatillo(
        @Path("nomPlatillo") nomPlatillo: String
    ): Response<PlatilloResponse>
    // END PLATILLOS
}
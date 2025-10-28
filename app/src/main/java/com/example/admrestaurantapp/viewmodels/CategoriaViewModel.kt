package com.example.admrestaurantapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admrestaurantapp.models.Categoria
import com.example.admrestaurantapp.network.Retrofit
import com.example.admrestaurantapp.response.CategoriaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CategoriaViewModel: ViewModel() {

    private var _listaCategorias = MutableStateFlow<List<Categoria>>(emptyList())
    val listaCategorias = _listaCategorias.asStateFlow()

    private lateinit var response: Response<CategoriaResponse>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.obtenerCategorias()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaCategorias.value = response.body()?.datos ?: emptyList()
                }
            }
        }
    }

    fun obtenerCategorias() {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.obtenerCategorias()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaCategorias.value = response.body()?.datos ?: emptyList()
                }
            }
        }
    }

    fun agregarCategoria(nomCategoria: String) {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.agregarCategoria(nomCategoria)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    obtenerCategorias()
                }
            }
        }
    }

    fun borrarCategoria(nomCategoria: String) {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.borrarCategoria(nomCategoria)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaCategorias.value = _listaCategorias.value.filter {
                        it.nomCategoria != nomCategoria
                    }
                }
            }
        }
    }

    fun validarCategoria(nomCategoria: String): Boolean {
        return nomCategoria.isNotEmpty()
    }
}
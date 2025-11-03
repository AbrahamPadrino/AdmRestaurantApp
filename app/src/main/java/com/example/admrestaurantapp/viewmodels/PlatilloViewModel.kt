package com.example.admrestaurantapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admrestaurantapp.models.Categoria
import com.example.admrestaurantapp.models.Platillo
import com.example.admrestaurantapp.network.Retrofit
import com.example.admrestaurantapp.response.CategoriaResponse
import com.example.admrestaurantapp.response.PlatilloResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PlatilloViewModel: ViewModel() {
    private var _listaPlatillos = MutableStateFlow<List<Platillo>>(emptyList())
    val listaPlatillos = _listaPlatillos.asStateFlow()

    private var _listaCategorias = MutableStateFlow<List<Categoria>>(emptyList())
    val listaCategorias = _listaCategorias.asStateFlow()

    private lateinit var resCategoria: Response<CategoriaResponse>
    private lateinit var response: Response<PlatilloResponse>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.obtenerPlatillos()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaPlatillos.value = response.body()?.datos ?: emptyList()
                }
            }
        }
    }

    fun obtenerPlatillos() {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.obtenerPlatillos()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaPlatillos.value = response.body()?.datos ?: emptyList()
                }
            }
        }
    }

    fun agregarPlatillo(platillo: Platillo) {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.agregarPlatillo(platillo)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    obtenerPlatillos()
                }
            }
        }
    }

    fun editarPlatillo(platillo: Platillo) {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.actualizarPlatillo(platillo.nomPlatillo, platillo)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    obtenerPlatillos()
                }
            }
        }
    }

    fun borrarPlatillo(nomPlatillo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            response = Retrofit.webService.borrarPlatillo(nomPlatillo)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaPlatillos.value = listaPlatillos.value.filter {
                        it.nomPlatillo != nomPlatillo
                    }
                }
            }
        }
    }

    fun obtenerCategorias() {
        viewModelScope.launch(Dispatchers.IO) {
            resCategoria = Retrofit.webService.obtenerCategorias()
            withContext(Dispatchers.Main) {
                if (resCategoria.body()!!.codigo == "200"){
                    _listaCategorias.value = resCategoria.body()?.datos ?: emptyList()
                }
            }
        }
    }

    fun validarCampos(
        nomPlatillo: String,
        precio: String,
        calPlatillo: String,
        catSeleccionado: String
    ): Boolean {
        return if (
            nomPlatillo.isEmpty() ||
            precio.isEmpty() ||
            calPlatillo.isEmpty() ||
            catSeleccionado.isEmpty()
        ) {
            false
        } else {
            true
        }
    }
}
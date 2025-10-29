package com.example.admrestaurantapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.admrestaurantapp.R
import com.example.admrestaurantapp.utils.Constantes
import com.example.admrestaurantapp.viewmodels.CategoriaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaView(
    navController: NavController,
    viewModel: CategoriaViewModel
) {
    val showDialogAdd = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "CATEGORIAS")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = colorResource(id = R.color.amor_secreto)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.valle_dorado),
                    titleContentColor = colorResource(id = R.color.amor_secreto)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialogAdd.value = true
                },
                containerColor = colorResource(id = R.color.azul_atlantico),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        if (showDialogAdd.value) {
            DialogCategoriaAdd(
                showDialogAdd,
                viewModel
            )
        }

        CategoriaScreen(
            it,
            viewModel
        )
    }
}

@Composable
fun DialogCategoriaAdd(
    showDialogAdd: MutableState<Boolean>,
    viewModel: CategoriaViewModel
) {
    var nomCategoria by remember { mutableStateOf("") }

    AlertDialog(
        title = {
            Text(text = "Agregar Categoria")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "La imagen de la categoria se tiene que subir desde una computadora al servidor.\n" +
                            "Contacta a TI para dicha acción."
                )

                OutlinedTextField(
                    value = nomCategoria,
                    onValueChange = {
                        nomCategoria = it
                    },
                    label = {
                        Text(text = "Introduce el nombre de la categoria")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    maxLines = 1,
                    singleLine = true
                )
            }
        },
        onDismissRequest = {
            showDialogAdd.value = false
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialogAdd.value = false
                }
            ) {
                Text(text = "Cancelar")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (viewModel.validarCategoria(nomCategoria)) {
                        viewModel.agregarCategoria(nomCategoria)
                        showDialogAdd.value = false
                    }
                }
            ) {
                Text(text = "Aceptar")
            }
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoriaScreen(
    paddingValues: PaddingValues,
    viewModel: CategoriaViewModel
) {
    val listaCategorias by viewModel.listaCategorias.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        columns = GridCells.Fixed(2)
    ) {
        items(listaCategorias) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.valle_dorado)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    IconButton(
                        modifier = Modifier
                            .width(30.dp)
                            .padding(end = 8.dp)
                            .align(Alignment.TopEnd),
                        onClick = {
                            viewModel.borrarCategoria(it.nomCategoria)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_bote_basura),
                            tint = Color.Unspecified,
                            contentDescription = "Borrar"
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        GlideImage(
                            modifier = Modifier
                                .width(220.dp)
                                .height(170.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Fit,
                            model = "${Constantes.PATH_IMG_CATEGORIAS}${it.imagenCategoria}",
                            contentDescription = "categoria",
                            alignment = Alignment.Center
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = colorResource(id = R.color.gris_oscuro))
                                .padding(12.dp),
                            text = it.nomCategoria.toUpperCase(),
                            textAlign = TextAlign.Center,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 26.sp,
                                color = colorResource(id = R.color.amor_secreto)
                            )
                        )
                    }
                }
            }
        }
    }
}
package com.example.admrestaurantapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.admrestaurantapp.R
import com.example.admrestaurantapp.models.Platillo
import com.example.admrestaurantapp.viewmodels.PlatilloViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatilloView(
    navController: NavController,
    viewModel: PlatilloViewModel
) {

    val showDialog = remember { mutableStateOf(false) }
    val platilloAddUpdate = remember { mutableStateOf("") }
    val platilloEdit = remember { mutableStateOf(Platillo()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "PLATILLOS")
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
                    showDialog.value = true
                    platilloAddUpdate.value = "add"
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
        if (showDialog.value) {
            DialogPlatilloAddEdit(
                platilloAddUpdate = platilloAddUpdate,
                showDialog = showDialog,
                viewModel = viewModel,
                platilloEdit = platilloEdit
            )
        }

        PlatilloScreen(
            paddingValues = it,
            platilloAddUpdate = platilloAddUpdate,
            showDialog = showDialog,
            viewModel = viewModel,
            platilloEdit = platilloEdit
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogPlatilloAddEdit(
    platilloAddUpdate: MutableState<String>,
    showDialog: MutableState<Boolean>,
    viewModel: PlatilloViewModel,
    platilloEdit: MutableState<Platillo>
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var nomPlatillo by remember { mutableStateOf("") }
    var descPlatillo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var calPlatillo by remember { mutableStateOf("") }

    var showCategorias by remember { mutableStateOf(false) }
    var catSeleccionado by remember { mutableStateOf("Seleccina una categoria") }

    if (platilloAddUpdate.value == "edit") {
        nomPlatillo = platilloEdit.value.nomPlatillo
        if (platilloEdit.value.descPlatillo.isNullOrEmpty()) {
            descPlatillo = ""
        } else {
            descPlatillo = platilloEdit.value.descPlatillo
        }
        precio = platilloEdit.value.precio.toString()
        catSeleccionado = platilloEdit.value.categoria
    }

    viewModel.obtenerCategorias()

    AlertDialog(
        title = {
            if (platilloAddUpdate.value == "add") {
                Text(text = "Agregar Platillo")
            } else {
                Text(text = "Editar Platillo")
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = nomPlatillo,
                    onValueChange = {
                        if (platilloAddUpdate.value == "add") {
                            nomPlatillo = it
                        }
                    },
                    label = {
                        Text(text = "Nombre del platillo")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    maxLines = 1,
                    singleLine = true
                )

                OutlinedTextField(
                    value = descPlatillo,
                    onValueChange = {
                        descPlatillo = it
                    },
                    label = {
                        Text(text = "Breve descripción del platillo")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    maxLines = 5,
                    singleLine = false
                )

                OutlinedTextField(
                    value = precio,
                    onValueChange = {
                        precio = it
                    },
                    label = {
                        Text(text = "Precio del platillo")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    maxLines = 1,
                    singleLine = true
                )

                OutlinedTextField(
                    value = calPlatillo,
                    onValueChange = {
                        calPlatillo = it
                    },
                    label = {
                        Text(text = "Calorias del platillo")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    maxLines = 1,
                    singleLine = true
                )

                ExposedDropdownMenuBox(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    expanded = showCategorias,
                    onExpandedChange = {
                        showCategorias = !showCategorias
                    }
                ) {
                    keyboardController?.hide()

                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        value = catSeleccionado,
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = showCategorias)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    ExposedDropdownMenu(
                        expanded = showCategorias,
                        onDismissRequest = {
                            showCategorias = false
                        }
                    ) {
                        viewModel.listaCategorias.value.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = s.nomCategoria)
                                },
                                onClick = {
                                    if (s.toString() != "") {
                                        catSeleccionado = s.nomCategoria
                                    }
                                    showCategorias = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }
            }
        },
        onDismissRequest = {
            showDialog.value = false
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                }
            ) {
                Text(text = "Cancelar")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (
                        viewModel.validarCampos(
                            nomPlatillo,
                            precio,
                            calPlatillo,
                            catSeleccionado
                        )
                    ) {
                        val platillo = Platillo(
                            nomPlatillo = nomPlatillo,
                            descPlatillo = descPlatillo,
                            precio = precio.toDouble(),
                            calPlatillo = calPlatillo.toDouble(),
                            categoria = catSeleccionado
                        )

                        if (platilloAddUpdate.value == "add") {
                            viewModel.agregarPlatillo(platillo)
                        }
                        else {
                            viewModel.editarPlatillo(platillo)
                        }

                        showDialog.value = false
                        viewModel.obtenerPlatillos()
                    }
                }
            ) {
                Text(text = "Aceptar")
            }
        }
    )
}

@Composable
fun PlatilloScreen(
    paddingValues: PaddingValues,
    platilloAddUpdate: MutableState<String>,
    showDialog: MutableState<Boolean>,
    viewModel: PlatilloViewModel,
    platilloEdit: MutableState<Platillo>
) {
    val listaPlatillos by viewModel.listaPlatillos.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(listaPlatillos) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = 12.dp,
                        horizontal = 28.dp
                    ),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.gris_oscuro),
                    contentColor = colorResource(id = R.color.amor_secreto)
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 8.dp),
                    text = it.nomPlatillo.toUpperCase(),
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Categoria: ${it.categoria}"
                )

                if (it.descPlatillo.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Descripción: ${it.descPlatillo}"
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Precio: ${it.precio}"
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    IconButton(
                        onClick = {
                            platilloAddUpdate.value = "edit"
                            showDialog.value = true
                            platilloEdit.value = it
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_edit),
                            tint = Color.Unspecified,
                            contentDescription = "editar"
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.borrarPlatillo(it.nomPlatillo)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_bote_basura),
                            tint = Color.Unspecified,
                            contentDescription = "borrar"
                        )
                    }
                }
            }
        }
    }
}
package com.example.admrestaurantapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.admrestaurantapp.R

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InicioView(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.valle_dorado)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(12.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            onClick = {
                navController.navigate("categoria")
            }
        ) {
            GlideImage(
                modifier = Modifier
                    .width(250.dp)
                    .height(200.dp),
                model = R.drawable.icon_lista,
                contentScale = ContentScale.Fit,
                contentDescription = "categoria",
                alignment = Alignment.Center
            )

            Text(
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.azul_atlantico))
                    .padding(12.dp),
                text = "CATEGORIAS",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 26.sp,
                    color = colorResource(id = R.color.amor_secreto)
                )
            )
        }

        Card(
            modifier = Modifier
                .padding(12.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            onClick = {
                navController.navigate("platillo")
            }
        ) {
            GlideImage(
                modifier = Modifier
                    .width(250.dp)
                    .height(200.dp),
                model = R.drawable.icon_platillos,
                contentScale = ContentScale.Fit,
                contentDescription = "platillos",
                alignment = Alignment.Center
            )

            Text(
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.azul_atlantico))
                    .padding(12.dp),
                text = "PLATILLOS",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 26.sp,
                    color = colorResource(id = R.color.amor_secreto)
                )
            )
        }
    }
}
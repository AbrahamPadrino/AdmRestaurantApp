package com.example.admrestaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.admrestaurantapp.navigate.NavManager
import com.example.admrestaurantapp.ui.theme.AdmRestaurantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdmRestaurantAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Inicializar el controlador de navegación
                    NavManager()

                }
            }
        }
    }
}

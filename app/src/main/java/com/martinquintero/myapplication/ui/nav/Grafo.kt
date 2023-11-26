package com.martinquintero.myapplication.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martinquintero.myapplication.CrearEquipo
import com.martinquintero.myapplication.JuegoGenshin
import com.martinquintero.myapplication.MostrarInfoPersonaje
import com.martinquintero.myapplication.ui.rutas.Rutas

@Composable
fun GrafoNavegacion(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.JuegoGenshin.ruta) {

        // "URL" -> Composable
        composable(Rutas.JuegoGenshin.ruta){
            JuegoGenshin(navController = navController)
        }

        composable(Rutas.CrearEquipo.ruta){
            CrearEquipo(navController = navController)
        }

        composable(Rutas.MostrarInfoPersonaje.ruta+"/{nombre}"){
            val nombre = it.arguments?.getString("nombre")?:""
            MostrarInfoPersonaje(navController = navController, nombre)
        }
    }
}
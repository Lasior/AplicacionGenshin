package com.martinquintero.myapplication.ui.rutas

sealed class Rutas(val ruta: String) {
    /* TODO
        Crear e identificar las rutas (diferentes pantallas) de nuestra app
     */
    object JuegoGenshin: Rutas("juegoGenshin")
    object CrearEquipo: Rutas("crearEquipo")
    object MostrarInfoPersonaje: Rutas("mostrarInfoPersonaje")
}
package com.martinquintero.myapplication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.martinquintero.myapplication.ui.rutas.Rutas

var listaEquipos: MutableList<Personaje> by mutableStateOf(mutableListOf(
    Personaje("Hu Tao","TaoHyperCarry","DPS On-field",
        R.drawable.hutao_046_icon, 36),
    Personaje("Yelan","TaoHyperCarry","DPS Off-field",
        R.drawable.yelan_060_icon, 36),
    Personaje("Furina","PermaFrost","Buffer",
        R.drawable.furina_089_icon, 36),
    Personaje("Jean","Nacional","Healer",
        R.drawable.jean_003_icon, 36),
))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuegoGenshin(navController : NavHostController) {

    var query by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    val ctx = LocalContext.current
    val onSearch:(String) -> Unit = {
        Toast.makeText(ctx,query,Toast.LENGTH_SHORT).show()
        active = false
    }
    SearchBar(query = query,
        onQueryChange = {query = it},
        onSearch = {
            onSearch(query)
        },
        active = active,
        onActiveChange = {active = it},
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        placeholder = {Text(text = "Busque su equipo")},
        leadingIcon = { IconButton(onClick={ })  {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
        }},
        trailingIcon = { IconButton(onClick={ if(query.isNotEmpty()) onSearch(query) })  {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }}
    ) {
        if (query.isEmpty()) {
            val filteredEquipos = listaEquipos.filter { it.equipo.contains(query, true) }
            filteredEquipos.forEach {item -> Text(
                text ="${item.equipo}",
                modifier = Modifier
                    .clickable {
                        Toast.makeText(ctx,item.equipo,Toast.LENGTH_SHORT).show()
                        active = false
                    }
            )}
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp,80.dp,20.dp,20.dp)
            .verticalScroll(rememberScrollState())) {

        Text("Bienvenido al creador de equipo",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(0.dp,0.dp,0.dp, 20.dp),
            fontWeight = FontWeight.Bold
        )

        mostrar(navController)

        Row(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 20.dp),
        ) {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Rutas.CrearEquipo.ruta) },
                icon = { Icon(Icons.Filled.Add, "Boton flotante de añadir equipo") },
                text = { Text(text = "Añadir") },
            )
            ExtendedFloatingActionButton(
                onClick = { /*onClick()*/ },
                icon = { Icon(Icons.Filled.Delete, "Boton flotante de borrar equipo") },
                text = { Text(text = "Borrar") },
                modifier = Modifier.padding(100.dp, 0.dp,0.dp, 0.dp)
            )
        }
    }
}

@Composable
fun mostrarEquipo (nombre: String, equipo: String, rol: String, imagen: Int, estrellas: Int, navController : NavHostController) {

    var color = Color.Cyan

    if (equipo == "Nacional") {
        color = Color.LightGray
    }
    if (equipo == "TaoHyperCarry") {
        color = Color.Red
    }
    if (equipo == "HyperBloom") {
        color = Color.Green
    }
    if (equipo == "PermaFrost") {
        color = Color.Cyan
    }
    if (equipo == "PornoGeo") {
        color = Color.Yellow
    }

    ExtendedFloatingActionButton(onClick = { navController.navigate(Rutas.MostrarInfoPersonaje.ruta+"/$nombre")},
        containerColor = color,
        modifier = Modifier.padding(0.dp,5.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(painter = painterResource(id = imagen),
                contentDescription = "",
                Modifier.padding(end = 25.dp)
               )
            Column() {
                Text("Nombre: "+nombre,
                    fontSize = 20.sp)
                Text("Rol: "+rol,
                    fontSize = 20.sp)
                Text("Estrellas conseguidas: "+estrellas,
                    fontSize = 20.sp)
            }
           /* val checked = remember { mutableStateOf(false) }
            Checkbox(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
            )*/
        }
    }
}

@Composable
fun mostrar (navController : NavHostController) {
    for (personaje in listaEquipos) {
        mostrarEquipo(personaje.nombre,personaje.equipo,personaje.rol,personaje.imagen,personaje.estrellas,navController)
    }
}
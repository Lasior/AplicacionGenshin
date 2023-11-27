package com.martinquintero.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.martinquintero.myapplication.ui.rutas.Rutas

var listaEquipos: MutableList<Personaje> by mutableStateOf(mutableListOf(
    Personaje("Shogun Raiden","HyperBloom","DPS On-field",
        R.drawable.shougun_052_icon, 36,"7/10/2023"),
    Personaje("Hu Tao","TaoHyperCarry","DPS On-field",
        R.drawable.hutao_046_icon, 36,"15/11/2023"),
    Personaje("Yelan","TaoHyperCarry","DPS Off-field",
        R.drawable.yelan_060_icon, 36,"15/11/2023"),
    Personaje("Furina","TaoHyperCarry","Buffer",
        R.drawable.furina_089_icon, 36,"27/11/2023"),
    Personaje("Noelle","PornoGeo","Healer",
        R.drawable.noel_034_icon, 36,"23/11/2023"),
    Personaje("Jean","Nacional","Healer",
        R.drawable.jean_003_icon, 36,"20/10/2023"),
    Personaje("Arataki Itto","PornoGeo","DPS On-field",
        R.drawable.itto_057_icon, 36,"23/11/2023"),
))

var listaRemover: MutableList<Personaje> by mutableStateOf(mutableListOf())

val equiposPosibles = arrayOf("PermaFrost", "Nacional", "HyperBloom", "PornoGeo", "TaoHyperCarry")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuegoGenshin(navController : NavHostController) {

    var query by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    val onSearch:(String) -> Unit = {
        query = it
        active = false
    }
    var borrar by remember {
        mutableStateOf(false)
    }
    var openDialog by remember {
        mutableStateOf(false)
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
        trailingIcon = { IconButton(
            onClick = { onSearch(query) },
            enabled = query.isNotEmpty()
        ){
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }}
    ) {
        val filteredEquipos = equiposPosibles.filter { it.contains(query, true) }
        filteredEquipos.forEach {item -> Text(
            text = item,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
                .clickable {
                    onSearch(item)
                }
        )}
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp, 80.dp, 20.dp, 20.dp)
            .verticalScroll(rememberScrollState())) {

        Text("Bienvenido al creador de equipo",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(0.dp,0.dp,0.dp, 20.dp),
            fontWeight = FontWeight.Bold
        )

        Mostrar(navController,query,borrar)

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
                onClick = {
                    if (!borrar) {
                        borrar = true
                    } else {
                        openDialog = true
                    }
                          },
                icon = { Icon(Icons.Filled.Delete, "Boton flotante de borrar equipo") },
                text = { Text(text = "Borrar") },
                modifier = Modifier.padding(100.dp, 0.dp,0.dp, 0.dp)
            )
        }
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = {
                Text(text = "Confirme el borrado")
            },
            text = {
                Text("¿Esta seguro de que quiere eliminar los personajes marcados?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        listaRemover.forEach { item ->
                            listaEquipos.remove(item)
                        }
                        borrar = false
                        openDialog = false
                    }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        borrar = false
                        openDialog = false
                    }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun MostrarEquipo (personaje: Personaje, navController : NavHostController, borrar: Boolean) {

    var color = Color.Cyan

    if (personaje.equipo == "Nacional") {
        color = Color.LightGray
    }
    if (personaje.equipo == "TaoHyperCarry") {
        color = Color.Red
    }
    if (personaje.equipo == "HyperBloom") {
        color = Color.Green
    }
    if (personaje.equipo == "PermaFrost") {
        color = Color.Cyan
    }
    if (personaje.equipo == "PornoGeo") {
        color = Color.Yellow
    }

    var checked by remember { mutableStateOf(false) }

    if (checked) {
        listaRemover.add(personaje)
    }
    val nombre = personaje.nombre

    ExtendedFloatingActionButton(onClick = { navController.navigate(Rutas.MostrarInfoPersonaje.ruta+"/$nombre")},
        containerColor = color,
        modifier = Modifier.padding(0.dp,5.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(painter = painterResource(id = personaje.imagen),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 30.dp,end = 30.dp)
            )
            Column() {
                Text("Nombre: "+personaje.nombre,
                    fontSize = 20.sp)
                Text("Rol: "+personaje.rol,
                    fontSize = 20.sp)
                Text("Estrellas conseguidas: "+personaje.estrellas,
                    fontSize = 20.sp)
                Text("Fecha: "+personaje.fecha,
                    fontSize = 20.sp)
            }
            if (borrar) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier
                        .padding(top = 30.dp)
                )
            }
        }
    }
}


@Composable
fun Mostrar (navController: NavHostController, query: String, borrar: Boolean) {
    for (personaje in listaEquipos) {
        if (query == personaje.equipo || query == "") {
            MostrarEquipo(personaje,navController, borrar)
        }
    }
}
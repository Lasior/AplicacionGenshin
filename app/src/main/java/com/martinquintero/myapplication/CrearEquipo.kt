package com.martinquintero.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

import com.martinquintero.myapplication.ui.rutas.Rutas
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearEquipo(navController : NavHostController) {

    data class Personaje(val nombre: String, val imagen: Int)

    val listaPersonajes = remember { mutableStateListOf<Personaje>(
        Personaje("Hu Tao",
            R.drawable.hutao_046_icon),
        Personaje("Yelan",
            R.drawable.yelan_060_icon),
        Personaje("Furina",
            R.drawable.furina_089_icon),
        Personaje("Jean",
            R.drawable.jean_003_icon),
        Personaje("Kaedehara Kazuha",
            R.drawable.kazuha_047_icon),
        Personaje("Wriothesley",
            R.drawable.wriothesley_086_icon),
        Personaje("Neuvillete",
            R.drawable.neuvillette_087_icon),
        Personaje("Zhongli",
            R.drawable.zhongli_030_icon),
        Personaje("Arataki Itto",
            R.drawable.itto_057_icon),
        Personaje("Noelle",
            R.drawable.noel_034_icon),
        Personaje("Shogun Raiden",
            R.drawable.shougun_052_icon),
    )}

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())) {

        Text("Introduce el nombre del personaje: ",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        var selectedNombre by remember { mutableStateOf(listaPersonajes[0].nombre) }
        var selectedImagen by remember { mutableIntStateOf(listaPersonajes[0].imagen) }
        var equipo by remember { mutableStateOf(equiposPosibles[0]) }

        Row() {
            var expanded by remember { mutableStateOf(false) }
            Box(
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    Row() {
                        Image(painter = painterResource(id = selectedImagen),
                            contentDescription = "",
                            modifier = Modifier
                                .size(60.dp)
                        )
                        TextField(
                            value = selectedNombre,
                            onValueChange = {selectedNombre = it},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .padding(start = 16.dp),
                            label = { Text("Nombre") })
                    }
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listaPersonajes.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row() {
                                        Image(painter = painterResource(id = item.imagen),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(60.dp))
                                        Text(text = item.nombre,
                                            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                                            fontSize = 20.sp,
                                        )
                                    } },
                                onClick = {
                                    selectedNombre = item.nombre
                                    selectedImagen = item.imagen
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Text("Introduce el rol del personaje: ",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(0.dp,10.dp)
        )

        val radioOptions = listOf("DPS On-field","DPS Off-field", "Support", "Buffer", "Healer")
        val (selectedRol, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
        Column {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedRol),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedRol),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium.merge(),
                        modifier = Modifier.padding(start = 16.dp,top = 15.dp)
                    )
                }
            }
        }

        Text("Introduce el numero de estrellas que consiguio: ",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        var sliderPosition by remember { mutableFloatStateOf(0f) }
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it.roundToInt().toFloat() },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                valueRange = 0f..36f
            )
            Text(text = sliderPosition.toInt().toString())
        }

        Text("Introduce el día que completo el Abismo: ",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        var fechaSeleccionada = datePickerView()
        if (fechaSeleccionada.isNullOrEmpty()) {
            fechaSeleccionada = "No Seleccionada"
        }

        Text("Introduce el equipo donde estaba: ",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Row() {
            var expanded by remember { mutableStateOf(false) }
            Box() {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    Row() {
                        TextField(
                            value = equipo,
                            onValueChange = {equipo = it},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .padding(start = 16.dp),
                            label = { Text("Nombre") })
                    }
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        equiposPosibles.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row() {
                                        Text(text = item,
                                            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                                            fontSize = 20.sp,
                                        )
                                    } },
                                onClick = {
                                    equipo = item
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Row(Modifier
            .fillMaxSize()
            .padding(10.dp, 20.dp)
        ) {
            ExtendedFloatingActionButton(
                onClick = {
                    listaEquipos.add(Personaje(selectedNombre,equipo,selectedRol,
                        selectedImagen,sliderPosition.toInt(),fechaSeleccionada.toString()))
                    navController.navigate(Rutas.JuegoGenshin.ruta)
                          },
                icon = { Icon(Icons.Filled.Add, "Boton flotante de crear equipo") },
                text = { Text(text = "Crear") },
            )
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Rutas.JuegoGenshin.ruta)},
                icon = { Icon(Icons.Filled.ArrowBack, "Boton flotante de volver atras") },
                text = { Text(text = "Volver") },
                modifier = Modifier.padding(100.dp, 0.dp,0.dp, 0.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerView(): String? {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DatePicker(
            state = datePickerState
        )
    }
    return selectedDate
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}
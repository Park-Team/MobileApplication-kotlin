package com.example.ezpark.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.ezpark.R
import com.example.ezpark.data.ParkingRepository
import com.example.ezpark.ui.theme.EzParkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentParking(
    onMenuClick: () -> Unit = {},
    onParkingAdded: () -> Unit = {}
) {    // Estados para los campos de texto
    val address = remember { mutableStateOf("") }
    val length = remember { mutableStateOf("") }
    val width = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("") }
    val startTime = remember { mutableStateOf("") }
    val endTime = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val pricePerHour = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    
    // Estados para controlar la visibilidad de los diálogos
    val showSuccessDialog = remember { mutableStateOf(false) }
    val showErrorDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "EzPark",
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = stringResource(R.string.menu_content_description)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
              // Dirección exacta
            Text(text = stringResource(R.string.address_label))
            OutlinedTextField(
                value = address.value,
                onValueChange = { address.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 2
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Espacio para la imagen (futuro mapa)
            Text(text = "Parking Space Image:")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.image_placeholder))
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Dimensiones en una fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {                // Longitud
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.length_label))
                    OutlinedTextField(
                        value = length.value,
                        onValueChange = { length.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                
                Spacer(modifier = Modifier.size(8.dp))
                
                // Ancho
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.width_label))
                    OutlinedTextField(
                        value = width.value,
                        onValueChange = { width.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                
                Spacer(modifier = Modifier.size(8.dp))
                
                // Altura
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.height_label))
                    OutlinedTextField(
                        value = height.value,
                        onValueChange = { height.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Horarios y teléfono
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {                // Hora de inicio
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.start_time_label))
                    OutlinedTextField(
                        value = startTime.value,
                        onValueChange = { startTime.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                Spacer(modifier = Modifier.size(8.dp))
                
                // Hora de fin
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.end_time_label))
                    OutlinedTextField(
                        value = endTime.value,
                        onValueChange = { endTime.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                Spacer(modifier = Modifier.size(8.dp))
                
                // Teléfono
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.phone_label))
                    OutlinedTextField(
                        value = phone.value,
                        onValueChange = { phone.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
              // Precio por hora
            Text(text = stringResource(R.string.price_per_hour_label))
            OutlinedTextField(
                value = pricePerHour.value,
                onValueChange = { pricePerHour.value = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Descripción corta
            Text(text = stringResource(R.string.short_description_label))
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                singleLine = false,
                maxLines = 5
            )
            
            Spacer(modifier = Modifier.height(24.dp))            // Botón de publicar estacionamiento
            Button(
                onClick = { 
                    // Validar que todos los campos obligatorios estén completos
                    if (address.value.isNotBlank() &&
                        length.value.isNotBlank() &&
                        width.value.isNotBlank() &&
                        height.value.isNotBlank() &&
                        startTime.value.isNotBlank() &&
                        endTime.value.isNotBlank() &&
                        phone.value.isNotBlank() &&
                        pricePerHour.value.isNotBlank() &&
                        description.value.isNotBlank()) {
                        
                        // Guardar el estacionamiento en el repositorio
                        ParkingRepository.addParkingSpace(
                            address = address.value,
                            length = length.value,
                            width = width.value,
                            height = height.value,
                            startTime = startTime.value,
                            endTime = endTime.value,
                            phone = phone.value,
                            pricePerHour = pricePerHour.value,
                            description = description.value
                        )
                            
                        // Todos los datos están completos, mostrar diálogo de éxito
                        showSuccessDialog.value = true
                    } else {
                        // Faltan datos, mostrar diálogo de error
                        showErrorDialog.value = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.post_parking_button))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }        // AlertDialog para éxito
        if (showSuccessDialog.value) {
            AlertDialog(
                onDismissRequest = { showSuccessDialog.value = false },
                title = { Text(text = stringResource(R.string.parking_added_title)) },
                confirmButton = {
                    TextButton(
                        onClick = { 
                            showSuccessDialog.value = false
                            onParkingAdded()
                        }
                    ) {
                        Text(stringResource(R.string.ok_button))
                    }
                }
            )
        }
        
        // AlertDialog para error de datos incompletos
        if (showErrorDialog.value) {
            AlertDialog(
                onDismissRequest = { showErrorDialog.value = false },
                title = { Text(text = stringResource(R.string.missing_data_title)) },
                confirmButton = {
                    TextButton(
                        onClick = { showErrorDialog.value = false }
                    ) {
                        Text(stringResource(R.string.ok_button))
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RentParkingPreview() {
    EzParkTheme {
        RentParking(
            onMenuClick = {},
            onParkingAdded = {}
        )
    }
}

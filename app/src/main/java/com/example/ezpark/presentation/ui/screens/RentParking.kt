package com.example.ezpark.presentation.ui.screens

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.ezpark.application.di.AppModule
import com.example.ezpark.presentation.ui.theme.ButtonColor
import com.example.ezpark.presentation.ui.theme.EzParkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentParking(
    onMenuClick: () -> Unit = {},
    onParkingAdded: () -> Unit = {}
) {
    // Estados para los campos del formulario
    val (address, setAddress) = remember { mutableStateOf("") }
    val (length, setLength) = remember { mutableStateOf("") }
    val (width, setWidth) = remember { mutableStateOf("") }
    val (height, setHeight) = remember { mutableStateOf("") }
    val (startTime, setStartTime) = remember { mutableStateOf("") }
    val (endTime, setEndTime) = remember { mutableStateOf("") }
    val (phone, setPhone) = remember { mutableStateOf("") }
    val (price, setPrice) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }

    // Estado para mostrar diálogo de confirmación
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    // Estado para errores en los campos
    val (errors, setErrors) = remember { mutableStateOf(mapOf<String, Boolean>()) }

    // Referencia al caso de uso
    val addParkingSpaceUseCase = AppModule.addParkingSpaceUseCase

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.rent_parking_title))
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = stringResource(R.string.menu_content_description)                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF212529),
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.register_parking_heading),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )

            // Dirección
            OutlinedTextField(
                value = address,
                onValueChange = setAddress,
                label = { Text(stringResource(R.string.address_label)) },
                modifier = Modifier.fillMaxWidth(),                isError = errors["address"] == true,
                supportingText = if (errors["address"] == true) {
                    { Text(text = stringResource(R.string.address_required)) }
                } else null
            )

            // Placeholder para el mapa (se implementará más adelante)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.image_placeholder))
            }

            // Dimensiones en una fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = length,
                    onValueChange = setLength,
                    label = { Text(stringResource(R.string.length_label)) },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errors["length"] == true,
                    supportingText = if (errors["length"] == true) {
                        { Text(text = stringResource(R.string.field_required)) }
                    } else null
                )
                OutlinedTextField(
                    value = width,
                    onValueChange = setWidth,
                    label = { Text(stringResource(R.string.width_label)) },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errors["width"] == true,
                    supportingText = if (errors["width"] == true) {
                        { Text(text = stringResource(R.string.field_required)) }
                    } else null
                )
                OutlinedTextField(
                    value = height,
                    onValueChange = setHeight,
                    label = { Text(stringResource(R.string.height_label)) },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errors["height"] == true,
                    supportingText = if (errors["height"] == true) {
                        { Text(text = stringResource(R.string.field_required)) }
                    } else null
                )
            }

            // Horarios en una fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = startTime,
                    onValueChange = setStartTime,
                    label = { Text(stringResource(R.string.start_time_label)) },
                    modifier = Modifier.weight(1f),
                    isError = errors["startTime"] == true,
                    supportingText = if (errors["startTime"] == true) {
                        { Text(text = stringResource(R.string.field_required)) }
                    } else null
                )
                
                OutlinedTextField(
                    value = endTime,
                    onValueChange = setEndTime,
                    label = { Text(stringResource(R.string.end_time_label)) },
                    modifier = Modifier.weight(1f),
                    isError = errors["endTime"] == true,
                    supportingText = if (errors["endTime"] == true) {
                        { Text(text = stringResource(R.string.field_required)) }
                    } else null
                )
            }
            
            // Teléfono
            OutlinedTextField(
                value = phone,
                onValueChange = setPhone,
                label = { Text(stringResource(R.string.phone_label)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = errors["phone"] == true,
                supportingText = if (errors["phone"] == true) {
                    { Text(text = stringResource(R.string.phone_invalid)) }                } else null
            )
            
            // Precio
            OutlinedTextField(
                value = price,
                onValueChange = setPrice,
                label = { Text(stringResource(R.string.price_per_hour_label)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = errors["price"] == true,
                supportingText = if (errors["price"] == true) {
                    { Text(text = stringResource(R.string.price_invalid)) }
                } else null
            )
            // Descripción
            OutlinedTextField(
                value = description,
                onValueChange = setDescription,
                label = { Text(stringResource(R.string.short_description_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                isError = errors["description"] == true,
                supportingText = if (errors["description"] == true) {
                    { Text(text = stringResource(R.string.description_required)) }
                } else null
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    // Validación básica de campos
                    val validationErrors = mutableMapOf<String, Boolean>()
                    
                    if (address.isBlank()) validationErrors["address"] = true
                    if (length.isBlank()) validationErrors["length"] = true
                    if (width.isBlank()) validationErrors["width"] = true
                    if (height.isBlank()) validationErrors["height"] = true
                    if (startTime.isBlank()) validationErrors["startTime"] = true
                    if (endTime.isBlank()) validationErrors["endTime"] = true
                    if (phone.isBlank()) validationErrors["phone"] = true
                    if (price.isBlank()) validationErrors["price"] = true
                    
                    setErrors(validationErrors)
                    
                    // Si no hay errores, mostrar diálogo de confirmación
                    if (validationErrors.isEmpty()) {
                        setShowDialog(true)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.post_parking_button))
            }
        }
    }

    // Diálogo de confirmación
    if (showDialog) {
        AlertDialog(            onDismissRequest = { setShowDialog(false) },
            title = { Text(stringResource(R.string.confirm_registration)) },
            text = { Text(stringResource(R.string.confirm_registration_question)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Llamar al caso de uso para añadir el estacionamiento
                        val success = addParkingSpaceUseCase(
                            address = address,
                            length = length,
                            width = width,
                            height = height,
                            startTime = startTime,
                            endTime = endTime,
                            phone = phone,
                            pricePerHour = price,
                            description = description
                        )
                        
                        if (success) {
                            onParkingAdded()
                        }
                        setShowDialog(false)                    }
                ) {
                    Text(stringResource(R.string.confirm_button))
                }
            },
            dismissButton = {
                TextButton(onClick = { setShowDialog(false) }) {
                    Text(stringResource(R.string.cancel_button))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RentParkingPreview() {
    EzParkTheme {
        RentParking()
    }
}

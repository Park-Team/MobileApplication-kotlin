package com.example.ezpark.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezpark.R
import com.example.ezpark.domain.model.ParkingSpace
import com.example.ezpark.presentation.ui.theme.ButtonColor
import com.example.ezpark.presentation.ui.theme.CardBackground
import com.example.ezpark.presentation.ui.theme.EzParkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingListScreen(
    onMenuClick: () -> Unit = {},
    onNewParkingClick: () -> Unit = {},
    parkingSpaces: List<ParkingSpace> = emptyList()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = stringResource(R.string.menu_content_description)
                        )
                    }
                },                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF212529),
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center            ) {
                Button(
                    onClick = onNewParkingClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.new_parking_button))
                }
            }
        }
    ) { paddingValues ->
        if (parkingSpaces.isEmpty()) {
            // Mostrar mensaje cuando no hay estacionamientos
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {                Text(
                    text = stringResource(R.string.no_parking_spaces),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            // Mostrar lista de estacionamientos
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = paddingValues.calculateBottomPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                items(parkingSpaces) { parking ->
                    ParkingItemCard(
                        parkingSpace = parking,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun ParkingItemCard(
    parkingSpace: ParkingSpace,
    modifier: Modifier = Modifier
) {    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {            Text(
                text = parkingSpace.address,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(R.string.dimensions_label) + ": ${parkingSpace.length} x ${parkingSpace.width} x ${parkingSpace.height} m",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = stringResource(R.string.time_label) + ": ${parkingSpace.startTime} - ${parkingSpace.endTime}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
              Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "${parkingSpace.description}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Divider(color = Color.White.copy(alpha = 0.5f))
              Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {                Text(
                    text = stringResource(R.string.price_per_hour_format, parkingSpace.pricePerHour.toString()),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingItemCardPreview() {
    EzParkTheme {
        ParkingItemCard(
            parkingSpace = ParkingSpace(
                id = "1",
                address = "123 Main St, Miraflores",
                length = 5.5,
                width = 2.5,
                height = 2.2,
                startTime = "8:00 AM",
                endTime = "6:00 PM",
                phone = "987-654-321",
                pricePerHour = 8.5,
                description = "Espacio techado cerca al parque Kennedy"
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingListScreenPreview() {
    EzParkTheme {
        ParkingListScreen(
            parkingSpaces = listOf(
                ParkingSpace(
                    id = "1",
                    address = "123 Main St, Miraflores",
                    length = 5.5,
                    width = 2.5,
                    height = 2.2,
                    startTime = "8:00 AM",
                    endTime = "6:00 PM",
                    phone = "987-654-321",
                    pricePerHour = 8.5,
                    description = "Espacio techado cerca al parque Kennedy"
                ),
                ParkingSpace(
                    id = "2",
                    address = "456 Oak Avenue, San Isidro",
                    length = 6.0,
                    width = 3.0,
                    height = 2.5,
                    startTime = "7:00 AM",
                    endTime = "10:00 PM",
                    phone = "912-345-678",
                    pricePerHour = 12.0,
                    description = "Estacionamiento seguro con vigilancia 24/7"
                )
            )
        )
    }
}

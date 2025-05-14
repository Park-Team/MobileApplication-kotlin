package com.example.ezpark.infrastructure.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.ezpark.domain.model.ParkingSpace
import com.example.ezpark.domain.repository.IParkingRepository
import java.util.UUID

/**
 * Implementación del repositorio para manejar los datos de estacionamientos
 */
class ParkingRepositoryImpl : IParkingRepository {
    private val _parkingSpaces = mutableStateListOf<ParkingSpace>()
    override val parkingSpaces: SnapshotStateList<ParkingSpace> = _parkingSpaces
    
    init {
        // Añadir algunos datos de ejemplo
        addParkingSpace(
            address = "123 Main St, Miraflores, Lima",
            length = "5.5",
            width = "2.5",
            height = "2.2",
            startTime = "8:00 AM",
            endTime = "6:00 PM",
            phone = "987-654-321",
            pricePerHour = "8.50",
            description = "Espacio techado cerca al parque Kennedy"
        )
        
        addParkingSpace(
            address = "456 Oak Avenue, San Isidro, Lima",
            length = "6.0",
            width = "3.0",
            height = "2.5",
            startTime = "7:00 AM",
            endTime = "10:00 PM",
            phone = "912-345-678",
            pricePerHour = "12.00",
            description = "Estacionamiento seguro con vigilancia 24/7"
        )
    }
    
    /**
     * Agrega un nuevo espacio de estacionamiento a la lista
     */
    override fun addParkingSpace(
        address: String,
        length: String,
        width: String,
        height: String,
        startTime: String,
        endTime: String,
        phone: String,
        pricePerHour: String,
        description: String
    ): Boolean {
        try {
            val parsedLength = length.toDoubleOrNull() ?: return false
            val parsedWidth = width.toDoubleOrNull() ?: return false
            val parsedHeight = height.toDoubleOrNull() ?: return false
            val parsedPrice = pricePerHour.toDoubleOrNull() ?: return false
            
            val parkingSpace = ParkingSpace(
                id = UUID.randomUUID().toString(),
                address = address,
                length = parsedLength,
                width = parsedWidth,
                height = parsedHeight,
                startTime = startTime,
                endTime = endTime,
                phone = phone,
                pricePerHour = parsedPrice,
                description = description
            )
            
            _parkingSpaces.add(parkingSpace)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}

package com.example.ezpark.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.ezpark.domain.model.ParkingSpace

/**
 * Interfaz para el repositorio de estacionamientos
 * Siguiendo los principios de DDD, definimos la interfaz en el dominio,
 * pero la implementación estará en la capa de infraestructura
 */
interface IParkingRepository {
    val parkingSpaces: SnapshotStateList<ParkingSpace>
    
    fun addParkingSpace(
        address: String,
        length: String,
        width: String,
        height: String,
        startTime: String,
        endTime: String,
        phone: String,
        pricePerHour: String,
        description: String
    ): Boolean
}

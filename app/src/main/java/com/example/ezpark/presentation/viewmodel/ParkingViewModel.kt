package com.example.ezpark.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.ezpark.application.di.AppModule
import com.example.ezpark.domain.model.ParkingSpace

/**
 * ViewModel para manejar la lógica de espacios de estacionamiento
 */
class ParkingViewModel : ViewModel() {
    private val addParkingSpaceUseCase = AppModule.addParkingSpaceUseCase
    private val getParkingSpacesUseCase = AppModule.getParkingSpacesUseCase
    
    // Exposición de datos a la UI
    val parkingSpaces: SnapshotStateList<ParkingSpace> 
        get() = getParkingSpacesUseCase() as SnapshotStateList<ParkingSpace>
    
    /**
     * Añade un nuevo espacio de estacionamiento
     */
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
    ): Boolean {
        return addParkingSpaceUseCase(
            address = address,
            length = length,
            width = width,
            height = height,
            startTime = startTime,
            endTime = endTime,
            phone = phone,
            pricePerHour = pricePerHour,
            description = description
        )
    }
}

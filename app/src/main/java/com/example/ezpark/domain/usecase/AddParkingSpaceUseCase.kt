package com.example.ezpark.domain.usecase

import com.example.ezpark.domain.repository.IParkingRepository

/**
 * Caso de uso para añadir un nuevo espacio de estacionamiento
 */
class AddParkingSpaceUseCase(private val parkingRepository: IParkingRepository) {
    
    operator fun invoke(
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
        return parkingRepository.addParkingSpace(
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

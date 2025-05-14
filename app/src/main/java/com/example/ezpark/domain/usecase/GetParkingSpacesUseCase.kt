package com.example.ezpark.domain.usecase

import com.example.ezpark.domain.model.ParkingSpace
import com.example.ezpark.domain.repository.IParkingRepository

/**
 * Caso de uso para obtener la lista de espacios de estacionamiento
 */
class GetParkingSpacesUseCase(private val parkingRepository: IParkingRepository) {
    
    operator fun invoke(): List<ParkingSpace> {
        return parkingRepository.parkingSpaces
    }
}

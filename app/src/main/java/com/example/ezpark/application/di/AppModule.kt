package com.example.ezpark.application.di

import com.example.ezpark.domain.repository.IParkingRepository
import com.example.ezpark.domain.usecase.AddParkingSpaceUseCase
import com.example.ezpark.domain.usecase.GetParkingSpacesUseCase
import com.example.ezpark.infrastructure.repository.ParkingRepositoryImpl

/**
 * Módulo principal de la aplicación para inyección de dependencias
 * (En un proyecto real, esto podría implementarse con Dagger Hilt o Koin)
 */
object AppModule {
    // Repositorios
    private val parkingRepository: IParkingRepository by lazy { ParkingRepositoryImpl() }
    
    // Casos de uso
    val getParkingSpacesUseCase: GetParkingSpacesUseCase by lazy { 
        GetParkingSpacesUseCase(parkingRepository) 
    }
    
    val addParkingSpaceUseCase: AddParkingSpaceUseCase by lazy { 
        AddParkingSpaceUseCase(parkingRepository) 
    }
}

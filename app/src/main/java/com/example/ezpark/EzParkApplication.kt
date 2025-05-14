package com.example.ezpark

import android.app.Application

/**
 * Clase principal de la aplicación
 * Punto de entrada para la inicialización de componentes globales
 */
class EzParkApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // Inicializar componentes globales
        // Por ejemplo, servicios de autenticación, bases de datos, etc.
    }
}

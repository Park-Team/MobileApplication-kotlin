package com.example.ezpark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ezpark.presentation.navigation.AppNavigation
import com.example.ezpark.presentation.ui.theme.EzParkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EzParkTheme {
                AppNavigation()
            }
        }
    }
}
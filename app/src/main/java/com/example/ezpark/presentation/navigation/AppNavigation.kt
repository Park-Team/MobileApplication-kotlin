package com.example.ezpark.presentation.navigation

import android.widget.Toast
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ezpark.application.di.AppModule
import com.example.ezpark.presentation.ui.components.AppDrawer
import com.example.ezpark.presentation.ui.screens.ParkingListScreen
import com.example.ezpark.presentation.ui.screens.RentParking
import com.example.ezpark.presentation.viewmodel.ParkingViewModel
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object ParkingList : Screen("parking_list")
    object RentParking : Screen("rent_parking")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: ParkingViewModel = remember { ParkingViewModel() }
) {
    // Manage drawer state
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    
    // Get current route for highlighting the correct item in the drawer
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Determine current screen
    val currentScreen = when (currentRoute) {
        Screen.ParkingList.route -> Screen.ParkingList
        Screen.RentParking.route -> Screen.RentParking
        else -> Screen.ParkingList
    }
    
    // Drawer functions
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    
    val closeDrawer: () -> Unit = {
        scope.launch {
            drawerState.close()
        }
    }
    
    // Navigation functions
    val navigate: (Screen) -> Unit = { screen ->
        scope.launch {
            drawerState.close()
        }
        navController.navigate(screen.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            popUpTo(Screen.ParkingList.route) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    
    // Drawer content
    AppDrawer(
        drawerState = drawerState,
        currentScreen = currentScreen,
        onNavigate = navigate,
        onLogout = {
            Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show()
        },
        onEditProfile = {
            Toast.makeText(context, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
        },
        onCloseDrawer = closeDrawer
    ) {
        // Navigation host
        NavHost(
            navController = navController,
            startDestination = Screen.ParkingList.route
        ) {
            composable(Screen.ParkingList.route) {
                ParkingListScreen(
                    onMenuClick = openDrawer,
                    onNewParkingClick = { navigate(Screen.RentParking) },
                    parkingSpaces = viewModel.parkingSpaces
                )
            }
            composable(Screen.RentParking.route) {
                RentParking(
                    onMenuClick = openDrawer,
                    onParkingAdded = {
                        navigate(Screen.ParkingList)
                        Toast.makeText(context, "Parking space added successfully!", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
}

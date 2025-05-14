package com.example.ezpark.navigation

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
import com.example.ezpark.data.ParkingRepository
import com.example.ezpark.ui.components.AppDrawer
import com.example.ezpark.ui.screens.ParkingListScreen
import com.example.ezpark.ui.screens.RentParking
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object ParkingList : Screen("parking_list")
    object RentParking : Screen("rent_parking")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
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
    
    val navigateToScreen: (Screen) -> Unit = { screen ->
        navController.navigate(screen.route) {
            // Pop up to the route to avoid building up a large stack of destinations
            popUpTo(Screen.ParkingList.route) {
                saveState = true
                inclusive = screen == Screen.ParkingList
            }
            // Avoid multiple copies of the same destination
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    
    // Mock functions for now - these would be implemented with actual auth logic later
    val onLogout = {
        Toast.makeText(context, "Logout functionality will be implemented in a future release", Toast.LENGTH_SHORT).show()
    }
    
    val onEditProfile = {
        Toast.makeText(context, "Profile editing will be implemented in a future release", Toast.LENGTH_SHORT).show()
    }
      val drawerContent: @Composable () -> Unit = {
        NavHost(
            navController = navController,
            startDestination = Screen.ParkingList.route
        ) {
            composable(route = Screen.ParkingList.route) {
                ParkingListScreen(
                    onMenuClick = openDrawer,
                    onNewParkingClick = {
                        navController.navigate(Screen.RentParking.route)
                    },
                    parkingSpaces = ParkingRepository.parkingSpaces
                )
            }
            
            composable(route = Screen.RentParking.route) {
                RentParking(
                    onMenuClick = openDrawer,
                    onParkingAdded = {
                        navController.navigate(Screen.ParkingList.route) {
                            popUpTo(Screen.ParkingList.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
      AppDrawer(
        drawerState = drawerState,
        currentScreen = currentScreen,
        onNavigate = navigateToScreen,
        onLogout = onLogout,
        onEditProfile = onEditProfile,
        onCloseDrawer = closeDrawer,
        content = drawerContent
    )
}

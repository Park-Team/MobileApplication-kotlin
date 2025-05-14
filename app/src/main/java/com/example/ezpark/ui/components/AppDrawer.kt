package com.example.ezpark.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ezpark.R
import com.example.ezpark.navigation.Screen

@Composable
fun AppDrawer(
    drawerState: DrawerState,
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    onLogout: () -> Unit,
    onEditProfile: () -> Unit,
    onCloseDrawer: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Perfil del usuario
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar del usuario
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User profile picture",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Nombre de usuario
                    Text(
                        text = "Juan Pérez",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    // Botón de editar perfil
                    TextButton(onClick = {
                        onEditProfile()
                        onCloseDrawer()
                    }) {
                        Text(stringResource(R.string.edit_profile))
                    }
                }
                
                Divider()
                
                // Elementos del menú
                NavigationDrawerItem(
                    label = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = null
                            )
                            Text(stringResource(R.string.find_parking))
                        }
                    },
                    selected = currentScreen == Screen.RentParking,
                    onClick = {
                        onNavigate(Screen.RentParking)
                        onCloseDrawer()
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                NavigationDrawerItem(
                    label = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null
                            )
                            Text(stringResource(R.string.rent_your_parking))
                        }
                    },
                    selected = currentScreen == Screen.ParkingList,
                    onClick = {
                        onNavigate(Screen.ParkingList)
                        onCloseDrawer()
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Divider()
                
                // Botón de cerrar sesión
                NavigationDrawerItem(
                    label = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = null
                            )
                            Text(stringResource(R.string.logout))
                        }
                    },
                    selected = false,
                    onClick = {
                        onLogout()
                        onCloseDrawer()
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        content = content
    )
}

package com.rvcode.skillaura.screens.freelancer.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rvcode.skillaura.util.FreelancerDestination



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreelancerApp() {
    val internalNavController = rememberNavController()

    val currentBackStackEntry by internalNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // Find the destination matching the current route
    val currentDestination = FreelancerDestination.navigationList
        .find { it.route == currentRoute } ?: FreelancerDestination.Home

    Scaffold(
        bottomBar = {
                FreeLancerBottomBar(navController = internalNavController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentDestination.title)
                },
                actions = {
                    if (currentDestination == FreelancerDestination.Profile) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu icon"
                            )
                        }
                    }
                }
            )
        }
    ) {innerPadding->
        NavHost(
            navController = internalNavController,
            startDestination = FreelancerDestination.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = FreelancerDestination.Home.route) {
                FreelancerHomeScreen()
            }
            composable(route = FreelancerDestination.InProgress.route) {
                InProgressScreen()
            }
            composable(route = FreelancerDestination.Profile.route) {
                ProfileScreen()
            }
        }
    }
}


@Composable
fun FreeLancerBottomBar(navController: NavHostController){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        FreelancerDestination.navigationList.forEach {destination->
            val selected = currentRoute==destination.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute!=destination.route){
                        navController.navigate(route = destination.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState =true
                            }
                            launchSingleTop= true
                            restoreState=true
                        }
                    }

                },
                icon = {
                    Icon(
                        imageVector = if(selected) destination.selectedIcon else destination.unSelectedIcon,
                        contentDescription = destination.title
                    )
                },
                label = {
                    Text(text = destination.title)
                }
            )

        }
    }
}
package com.rvcode.skillaura

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rvcode.skillaura.screens.HomeScreen
import com.rvcode.skillaura.screens.auth.screens.LoginScreen
import com.rvcode.skillaura.screens.auth.screens.RegistrationScreen
import com.rvcode.skillaura.util.Destination

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ){
        composable<Destination.Home> {
            HomeScreen(
                onNavigate = {
                    navController.navigate(Destination.Login){
                        popUpTo<Destination.Home> {
                            inclusive= true
                        }
                    }
                }
            )

        }
        composable<Destination.Login> {
            LoginScreen()
        }

        composable<Destination.Registration> {
            RegistrationScreen()
        }
    }
}
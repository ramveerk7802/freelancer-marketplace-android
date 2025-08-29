package com.rvcode.skillaura

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rvcode.skillaura.screens.HomeScreen
import com.rvcode.skillaura.screens.SplashScreen
import com.rvcode.skillaura.screens.auth.screens.LoginScreen
import com.rvcode.skillaura.screens.auth.screens.RegistrationScreen
import com.rvcode.skillaura.screens.client.screens.ClientHomeScreen
import com.rvcode.skillaura.screens.freelancer.screens.FreelancerApp
import com.rvcode.skillaura.screens.freelancer.screens.FreelancerHomeScreen
import com.rvcode.skillaura.util.Destination
import com.rvcode.skillaura.util.TokenManager
import com.rvcode.skillaura.viewmodels.AuthViewModel
import kotlin.math.sin

@Composable
fun AppNavigation(){
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Destination.Splash
    ){
        composable<Destination.Splash> {
            SplashScreen{loggedIn->
                if(loggedIn){
                    navController.navigate(Destination.Main){
                        popUpTo<Destination.Splash> {
                            inclusive=true
                        }
                        launchSingleTop=true
                    }
                }else{
                    navController.navigate(Destination.Login){
                        popUpTo<Destination.Splash> {
                            inclusive=true
                        }
                        launchSingleTop=true
                    }
                }

            }
        }
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
            LoginScreen(
                onclickNewAccount = {
                    navController.navigate(Destination.Registration){
                        popUpTo <Destination.Login>{
                            inclusive= true
                        }
                    }
                },
                onSuccessLogin = {
                    navController.navigate(Destination.Main){
                        popUpTo<Destination.Login> {
                            inclusive=true
                        }
                        launchSingleTop=true
                    }
                }
            )
        }

        composable<Destination.Registration> {
            RegistrationScreen{
                navController.navigate(Destination.Login){
                    popUpTo<Destination.Registration> {
                        inclusive=true
                    }
                }
            }
        }

        composable<Destination.Main>{
            MainScreen(
                onNavigateClient = {
                    navController.navigate(Destination.Client){
                        popUpTo<Destination.Main> {
                            inclusive =true
                        }
                        launchSingleTop=true
                    }
                },
                onNavigateFreelancer = {
                    navController.navigate(Destination.Freelancer){
                        popUpTo<Destination.Main> {
                            inclusive =true
                        }
                        launchSingleTop=true
                    }

                }
            )
        }

        composable<Destination.Client> {
            ClientHomeScreen()
        }
        composable<Destination.Freelancer> {
            FreelancerApp()
        }
    }

}
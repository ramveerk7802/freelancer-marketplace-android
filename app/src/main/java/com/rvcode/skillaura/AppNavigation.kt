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
import com.rvcode.skillaura.screens.auth.screens.LoginScreen
import com.rvcode.skillaura.screens.auth.screens.RegistrationScreen
import com.rvcode.skillaura.util.Destination
import com.rvcode.skillaura.util.TokenManager
import com.rvcode.skillaura.viewmodels.AuthViewModel

@Composable
fun AppNavigation(viewModel: AuthViewModel = hiltViewModel() ){
    val navController = rememberNavController()
    val isLoggedIn = viewModel.isLoggedIn.observeAsState().value
    val startDestination = remember { mutableStateOf<Any>(Destination.Splash) }
    isLoggedIn?.let {
        LaunchedEffect(key1 = Unit){
            if(it){
                startDestination.value= Destination.Home
            }else{
                startDestination.value=Destination.Login
            }
        }
    }


    if(startDestination.value!=Destination.Splash){
        NavHost(
            navController = navController,
            startDestination = startDestination.value
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
                LoginScreen(
                    onclickNewAccount = {
                        navController.navigate(Destination.Registration){
                            popUpTo <Destination.Login>{
                                inclusive= true
                            }
                        }
                    },
                    onSuccessLogin = {}
                )
            }

            composable<Destination.Registration> {
                RegistrationScreen()
            }
        }

    }

}
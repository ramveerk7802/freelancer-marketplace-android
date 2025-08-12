package com.rvcode.skillaura.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rvcode.skillaura.viewmodels.AuthViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(viewModel: AuthViewModel = hiltViewModel() ,onNavigate:(Boolean)-> Unit) {

    val isLoggedIn = viewModel.isLoggedIn.observeAsState()
    var hasNavigated by remember { mutableStateOf(false) }
    LaunchedEffect(isLoggedIn.value){
        if(!hasNavigated && isLoggedIn.value!=null){
            hasNavigated=true
            delay(500)
            onNavigate(isLoggedIn.value==true)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Splash Screen")
    }
}
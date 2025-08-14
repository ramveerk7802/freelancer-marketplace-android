package com.rvcode.skillaura

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.toUpperCase
import androidx.hilt.navigation.compose.hiltViewModel
import com.rvcode.skillaura.models.Role
import com.rvcode.skillaura.viewmodels.UserViewModel

@Composable
fun MainScreen(viewModel: UserViewModel= hiltViewModel(),onNavigateClient:()-> Unit, onNavigateFreelancer: () -> Unit){

    val role = viewModel.role.observeAsState().value
    role?.let {
        if(role.equals(Role.CLIENT.name, ignoreCase = true)){
            onNavigateClient()
        }else{
            onNavigateFreelancer()
        }
    }

}
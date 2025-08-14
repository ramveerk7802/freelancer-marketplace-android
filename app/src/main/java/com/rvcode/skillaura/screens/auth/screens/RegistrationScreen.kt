package com.rvcode.skillaura.screens.auth.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rvcode.skillaura.compose.utility.InputText
import com.rvcode.skillaura.compose.utility.LoadingDotsAnimation
import com.rvcode.skillaura.compose.utility.MyFilledButton
import com.rvcode.skillaura.compose.utility.PasswordText
import com.rvcode.skillaura.compose.utility.SelectOption
import com.rvcode.skillaura.models.auth.RegisterRequest
import com.rvcode.skillaura.util.Constant
import com.rvcode.skillaura.viewmodels.AuthViewModel

@Composable
fun RegistrationScreen(onNavigate:()-> Unit){
    val viewModel: AuthViewModel = hiltViewModel()
    val isLoading = viewModel.isLoading.observeAsState(false).value
    val context = LocalContext.current
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val roleState= remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        if(isLoading){
            LoadingDotsAnimation()
        }
        Text(
            text = "Registration",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        InputText(state = nameState, label = "Name", placeHolder = "Enter the name", leadingIcon = Icons.Outlined.Person)
        Spacer(Modifier.height(16.dp))
        InputText(state = emailState, label = "Email", placeHolder = "example@gamil.com", leadingIcon = Icons.Outlined.Email)
        Spacer(Modifier.height(16.dp))
        PasswordText(state = passwordState, label = "Password", placeHolder = "Enter the password", leadingIcon = Icons.Outlined.Lock)
        Spacer(modifier = Modifier.height(16.dp))
        SelectOption(state = roleState, label = "Selected role", dropDownList = Constant.dropDownRoleList)
        Spacer(modifier = Modifier.height(16.dp))
        MyFilledButton(text = "Register"){
           when{
               nameState.value.isBlank()->{
                   Toast.makeText(context,"Please enter your name.", Toast.LENGTH_SHORT).show()
               }
               emailState.value.isBlank()->{
                   Toast.makeText(context,"Please enter your email.", Toast.LENGTH_SHORT).show()
               }
               passwordState.value.isBlank()->{
                   Toast.makeText(context,"Please enter your password.", Toast.LENGTH_SHORT).show()
               }
               else->{
                   val request = RegisterRequest(
                       name = nameState.value,
                       email = emailState.value,
                       password =  passwordState.value,
                       role = roleState.value
                   )
                   viewModel.registerUser(
                       request,
                       onSuccess = {
                            onNavigate()
                       }
                   )
               }

           }
        }


    }

}
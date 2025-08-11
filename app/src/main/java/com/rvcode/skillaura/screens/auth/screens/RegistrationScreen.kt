package com.rvcode.skillaura.screens.auth.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rvcode.skillaura.compose.utility.InputText
import com.rvcode.skillaura.compose.utility.MyFilledButton
import com.rvcode.skillaura.compose.utility.PasswordText

@Composable
fun RegistrationScreen(){

    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
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
        MyFilledButton(text = "Register"){

        }


    }

}
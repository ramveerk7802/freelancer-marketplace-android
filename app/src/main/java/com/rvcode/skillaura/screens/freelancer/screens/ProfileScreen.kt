package com.rvcode.skillaura.screens.freelancer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rvcode.skillaura.compose.utility.CircleImage
import com.rvcode.skillaura.compose.utility.InputText

@Preview
@Composable
fun ProfileScreen() {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        CircleImage()
        Text(
            text = "Headline",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        InputText(state = nameState, label = "Name", placeHolder = "Your name", leadingIcon = Icons.Outlined.Person)
        InputText(state = emailState, label = "Email", singleLine = true, placeHolder = "Your email", leadingIcon = Icons.Outlined.Email)
        InputText(state = nameState, label = "Address", placeHolder = "Your address", leadingIcon = Icons.Outlined.LocationOn)


    }
}
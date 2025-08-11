package com.rvcode.skillaura.compose.utility

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.rvcode.skillaura.R

@Composable
fun InputText(state:MutableState<String>,label:String,placeHolder:String,leadingIcon:ImageVector){
    OutlinedTextField(
        onValueChange = {state.value=it},
        value = state.value,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "leading icon"
            )
        },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeHolder)

        }
    )
}

@Composable
fun PasswordText(state:MutableState<String>,label:String,placeHolder:String,leadingIcon:ImageVector){
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        onValueChange = {state.value=it},
        value = state.value,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "leading icon"
            )
        },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeHolder)

        },
        trailingIcon = {
            val image = if(showPassword.value) R.drawable.eye_open else R.drawable.eye_close
            IconButton(
                onClick = {
                    showPassword.value = !showPassword.value
                }
            ){
                Icon(
                    painter = painterResource(image),
                    contentDescription = "passwordIcon"
                )

            }
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true

    )
}

@Composable
fun MyFilledButton(text:String,onClick:()->Unit){
    Button(
        onClick = {
            onClick()
        }
    ){
        Text(text = text)
    }
}

@Composable
fun MyOutlinedButton(text:String,onClick:()->Unit){
    OutlinedButton(
        onClick = {
            onClick()
        }
    ){
        Text(text = text)
    }
}
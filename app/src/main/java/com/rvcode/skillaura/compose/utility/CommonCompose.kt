package com.rvcode.skillaura.compose.utility

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationState
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rvcode.skillaura.R
import com.rvcode.skillaura.models.requests.Role
import okio.Options

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

        },
        singleLine = true
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

@Composable
fun SelectOption(state: MutableState<String>,label: String,dropDownList: List<Role>){
    var selectedOption by remember { mutableIntStateOf(1) }
    var showList by remember { mutableStateOf(false) }
    state.value = dropDownList[selectedOption].name
    Box{
        OutlinedTextField(
            value = state.value,
            onValueChange = {state.value=it},
            label = {
                Text(text = label)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showList=!showList
                    }
                ){
                    Icon(
                        imageVector = if(showList) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "icon"
                    )
                }
            },
            singleLine = true,
            readOnly = true


        )
        DropdownMenu(
            expanded = showList,
            onDismissRequest = {showList=false}
        ){
            dropDownList.forEachIndexed { index,option->
                DropdownMenuItem(
                 text = {Text(text = option.name)},
                    onClick = {
                        selectedOption=index
                        showList=false
                    }
                )
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingDotsAnimation(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation_file))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Dialog(
        onDismissRequest = {}
    ){

        LottieAnimation(
            composition,
            progress = {progress}
        )

    }
}
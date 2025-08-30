package com.rvcode.skillaura.compose.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rvcode.skillaura.R
import com.rvcode.skillaura.models.Role
import com.rvcode.skillaura.models.project.ProjectResponse
import com.rvcode.skillaura.ui.theme.LIGHT_BLUE
import com.rvcode.skillaura.ui.theme.LIGHT_BLUE_TEXT_COLOR
import com.rvcode.skillaura.ui.theme.bg_color1
import com.rvcode.skillaura.ui.theme.bg_color2
import com.rvcode.skillaura.ui.theme.bg_color3

@Composable
fun InputText(state:MutableState<String>,singleLine:Boolean=false,label:String,placeHolder:String,leadingIcon:ImageVector ?=null){
    OutlinedTextField(
        onValueChange = {state.value=it},
        value = state.value,
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "leading icon"
                )
            }

        },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeHolder)

        },
        singleLine = singleLine
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
                        state.value = dropDownList[selectedOption].name
                        showList=false
                    }
                )
            }

        }
    }
}

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


@Composable
fun ProjectView(leftButtonText:String, rightButtonText: String, project: ProjectResponse, onLeftClick:()-> Unit, onRightClick:()-> Unit){
    Surface(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.DarkGray,
        ),
        modifier = Modifier.fillMaxWidth()


    ) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                Text(
                    text = project.status?.name?:"",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = LIGHT_BLUE_TEXT_COLOR,
                    modifier = Modifier.background(
                        color = LIGHT_BLUE,
                        shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                    )


                Row {
                    Text(
                        text ="Title : ",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray

                    )
                    Text(
                        text =project.title?:"",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,

                        )
                }



            }

            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().height(1.dp)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                        text = "Project Detail :",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                )


                Row {
                    Text(
                        text ="Budget : ",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray

                    )
                    Text(
                        text =project.budget?.toString()?:"",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,

                        )
                }



            }

            //description text
            Spacer(modifier = Modifier.fillMaxWidth().height(5.dp))
            Text(
                text = project.description?:"",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.W300,
                modifier = Modifier.fillMaxWidth().clickable {

                },
                maxLines = 4,

            )


            HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))

            //Outlined button

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){

                OutlinedButton(
                    border = BorderStroke(
                        color = Color.Blue,
                        width = 1.dp
                    ),
                    onClick = {
                        onLeftClick()
                    }
                ){

                    Text(
                        text = leftButtonText,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = Color.Blue,
                        fontSize = 18.sp


                    )
                }
                VerticalDivider(modifier = Modifier.height(32.dp))
                OutlinedButton(
                    border = BorderStroke(
                        color = Color.Red,
                        width = 1.dp
                    ),
                    onClick = {
                        onRightClick()
                    }
                ){

                    Text(
                        text = rightButtonText,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = Color.Red,
                        fontSize = 18.sp
                    )
                }
            }


        }
    }
}

@Composable
fun CircleImage(){

    AsyncImage(
        model = R.drawable.person_avtar,
        contentDescription = "Profile image",
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.person_avtar),
        modifier = Modifier.size(100.dp)
            .clip(shape = CircleShape)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = Color.Magenta
                ),
                shape = CircleShape
            )
            .padding(3.dp),
        clipToBounds = true
    )


}

fun Modifier.gradientBackground(): Modifier = this.then(
    Modifier.drawWithCache {
        onDrawBehind {
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF0682A5),
                        Color(0xFF223056),
                        Color(0xFF0F172A)
                    ),
                    center = Offset(
                        size.width * -0.247f,
                        size.height * -0.473f
                    ),
                    radius = size.maxDimension * 1.2f
                )
            )
        }
    }
)

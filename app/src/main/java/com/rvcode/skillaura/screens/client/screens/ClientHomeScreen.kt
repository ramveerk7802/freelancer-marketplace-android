package com.rvcode.skillaura.screens.client.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rvcode.skillaura.compose.utility.ProjectView

@Composable
fun ClientHomeScreen(){
    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(20){
            ProjectView(
                leftButtonText = "Edit",
                rightButtonText = "Delete",
                onLeftClick = {},
                onRightClick = {}
            )
        }
    }
}
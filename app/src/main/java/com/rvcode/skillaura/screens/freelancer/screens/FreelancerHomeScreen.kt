package com.rvcode.skillaura.screens.freelancer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.rvcode.skillaura.compose.utility.LoadingDotsAnimation
import com.rvcode.skillaura.compose.utility.ProjectView
import com.rvcode.skillaura.ui.theme.Background_base_color
import com.rvcode.skillaura.viewmodels.ProjectViewModel


@Composable
fun FreelancerHomeScreen() {

    val internalNavController = rememberNavController()

    val viewModel : ProjectViewModel = hiltViewModel()
    val projects = viewModel.projects.observeAsState(emptyList()).value
    val isProgress = viewModel.isProgress.observeAsState(false).value

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        if(isProgress){
            LoadingDotsAnimation()
        }

        LazyColumn (
            modifier = Modifier.fillMaxSize().padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            items(projects){project->
                ProjectView(
                    leftButtonText = "Edit",
                    rightButtonText = "Delete",
                    project = project,
                    onLeftClick = {},
                    onRightClick = {}
                )
            }
        }
    }
}
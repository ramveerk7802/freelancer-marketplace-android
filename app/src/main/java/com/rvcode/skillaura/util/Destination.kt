package com.rvcode.skillaura.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Home : Destination()

    @Serializable
    data object Login :Destination()

    @Serializable
    data object Registration : Destination()

}
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

    @Serializable
    data object Splash:Destination()

    @Serializable
    data object Main : Destination()

    @Serializable
    data object Client : Destination()

    @Serializable
    data object Freelancer : Destination()

}
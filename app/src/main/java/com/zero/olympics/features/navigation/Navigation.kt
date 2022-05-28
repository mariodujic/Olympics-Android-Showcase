package com.zero.olympics.features.navigation

sealed class Navigation(val route: String)
object Splash : Navigation("splash")
object Athletes : Navigation("athletes")
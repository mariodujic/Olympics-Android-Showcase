package com.zero.olympics.navigation

sealed class Navigation(val route: String)
object Splash : Navigation("splash")
object Athletes : Navigation("athletes")
object AthleteDetails : Navigation("details/{athleteId}") {
    const val id = "athleteId"
    const val ROUTE_WITHOUT_ARGUMENT = "details/"
}
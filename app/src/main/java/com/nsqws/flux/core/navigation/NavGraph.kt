package com.nsqws.flux.core.navigation

import com.nsqws.flux.R

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}

sealed class Screen(val route: String, val title: String? = null, val iconRes: Int? = null) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Recovery : Screen("recovery")

    object Home : Screen("home_view", "Inicio", R.drawable.home)
    object Cobrar : Screen("cobrar_view", "Pago", R.drawable.qr)
    object Ventas : Screen("ventas_view", "Ventas", R.drawable.document)
    object Perfil : Screen("perfil_view", "Perfil", R.drawable.user)
}
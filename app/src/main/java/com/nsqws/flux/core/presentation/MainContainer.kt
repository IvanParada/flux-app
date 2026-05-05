package com.nsqws.flux.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nsqws.flux.core.navigation.Graph
import com.nsqws.flux.core.navigation.Screen
import com.nsqws.flux.features.history.presentation.HistoryRoute
import com.nsqws.flux.features.home.presentation.HomeRoute
import com.nsqws.flux.features.payment.presentation.PaymentRoute

@Composable
fun MainContainer(rootNavController: NavHostController) {
    val nestedNavController = rememberNavController()
    val screens = listOf(Screen.Home, Screen.Cobrar, Screen.Ventas, Screen.Perfil)
    val appColor = MaterialTheme.colorScheme

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = appColor.surface,
                contentColor = appColor.primary,
            ){
                val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                screens.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(screen.title ?: "") },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconRes!!),
                                contentDescription = null
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = appColor.primary,
                            selectedTextColor = appColor.primary,
                            unselectedIconColor = appColor.secondary,
                            unselectedTextColor = appColor.secondary,
                            indicatorColor = appColor.primary.copy(alpha = 0.12f)
                        ),
                        onClick = {
                            nestedNavController.navigate(screen.route) {
                                popUpTo(nestedNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = nestedNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeRoute() }
            composable(Screen.Ventas.route) { HistoryRoute() }
            composable(Screen.Cobrar.route) { PaymentRoute() }
            composable(Screen.Perfil.route) {
                Button(onClick = {
                    rootNavController.navigate(Graph.AUTH) {
                        popUpTo(Graph.MAIN) { inclusive = true }
                    }
                }) { Text("Cerrar Sesión") }
            }
        }
    }
}
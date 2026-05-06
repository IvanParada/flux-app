package com.nsqws.flux

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.core.navigation.Graph
import com.nsqws.flux.core.navigation.Screen
import com.nsqws.flux.core.presentation.MainContainer
import com.nsqws.flux.features.auth.presentation.login.LoginRoute
import com.nsqws.flux.features.auth.presentation.recovery.RecoveryRoute
import com.nsqws.flux.features.auth.presentation.register.RegisterRoute
import com.nsqws.flux.features.auth.presentation.welcome.AuthWelcomeScreen
import com.nsqws.flux.ui.theme.FluxTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )

        super.onCreate(savedInstanceState)
        setContent {
            FluxTheme {
                val rootNavController = rememberNavController()
                val token by tokenManager.token.collectAsState(initial = "loading")

                if (token != "loading") {
                    NavHost(
                        navController = rootNavController,
                        startDestination = if (!token.isNullOrEmpty()) Graph.MAIN else Graph.AUTH,
                        route = Graph.ROOT
                    ) {
                        navigation(route = Graph.AUTH, startDestination = Screen.Welcome.route) {
                            composable(Screen.Welcome.route) {
                                AuthWelcomeScreen(
                                    onNavigateToLogin = { rootNavController.navigate(Screen.Login.route) },
                                    onNavigateToRegister = { rootNavController.navigate(Screen.Register.route) }
                                )
                            }
                            composable(Screen.Login.route) {
                                LoginRoute(
                                    onLoginSuccess = {
                                        rootNavController.navigate(Graph.MAIN) {
                                            popUpTo(Graph.AUTH) { inclusive = true }
                                        }
                                    },
                                    onNavigateToRegister = { rootNavController.navigate(Screen.Register.route) },
                                    onNavigateToRecovery = { rootNavController.navigate(Screen.Recovery.route) }
                                )
                            }
                            composable(Screen.Register.route) {
                                RegisterRoute(onNavigateToLogin = { rootNavController.popBackStack() })
                            }
                            composable(Screen.Recovery.route) {
                                RecoveryRoute(onNavigateToLogin = { rootNavController.popBackStack() })
                            }
                        }

                        composable(route = Graph.MAIN) {
                            MainContainer(rootNavController = rootNavController)
                        }
                    }
                }
            }
        }
    }
}
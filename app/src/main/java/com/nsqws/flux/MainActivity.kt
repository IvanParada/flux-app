package com.nsqws.flux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nsqws.flux.features.auth.presentation.login.LoginRoute
import com.nsqws.flux.features.auth.presentation.recovery.RecoveryRoute
import com.nsqws.flux.features.auth.presentation.register.RegisterRoute
import com.nsqws.flux.ui.theme.FluxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluxTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("login") {
                            LoginRoute(
                                onLoginSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onNavigateToRegister = {
                                    navController.navigate("register")
                                },
                                onNavigateToRecovery = {
                                    navController.navigate("recovery")
                                }
                            )
                        }

                        composable("recovery") {
                            RecoveryRoute(
                                onNavigateToLogin = {
                                    navController.navigate("login") {
                                        popUpTo("recovery") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable("register") {
                            RegisterRoute(
                                onNavigateToLogin = {
                                    navController.navigate("login") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable("home") {
                            Text("Soy el Home")
                        }
                    }
                }
            }
        }
    }
}
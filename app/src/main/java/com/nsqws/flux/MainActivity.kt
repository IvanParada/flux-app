package com.nsqws.flux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.features.auth.presentation.login.LoginRoute
import com.nsqws.flux.features.auth.presentation.recovery.RecoveryRoute
import com.nsqws.flux.features.auth.presentation.register.RegisterRoute
import com.nsqws.flux.features.auth.presentation.welcome.AuthWelcomeScreen
import com.nsqws.flux.ui.theme.FluxTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluxTheme {
                val navController = rememberNavController()
                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    val token = tokenManager.token.first()
                    startDestination = if (!token.isNullOrEmpty()) "home" else "welcome"
                }
                startDestination?.let { destination ->

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = destination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("welcome") {
                            AuthWelcomeScreen(
                                onNavigateToLogin = {
                                    navController.navigate("login")
                                },
                                onNavigateToRegister = {
                                    navController.navigate("register")
                                }
                            )
                        }

                        composable("login") {
                            LoginRoute(
                                onLoginSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("welcome") { inclusive = true }
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
                                        popUpTo("welcome")
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
}
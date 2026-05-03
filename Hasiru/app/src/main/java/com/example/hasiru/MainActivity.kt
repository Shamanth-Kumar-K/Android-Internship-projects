package com.example.hasiru

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hasiru.screens.*
import com.example.hasiru.screens.getPlantById
import com.example.hasiru.ui.theme.HasiruTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HasiruTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate("welcome") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("welcome") {
            WelcomeScreen(
                onGoogleSignIn = {},
                onContinueWithEmail = { navController.navigate("login") },
                onCreateAccount = { navController.navigate("signup") }
            )
        }

        composable("login") {
            LoginScreen(
                onSignIn = { _, _ ->
                    navController.navigate("main") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onSignUp = { navController.navigate("signup") },
                onForgotPassword = { navController.navigate("forgot") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("signup") {
            SignUpScreen(
                onSignUp = { _, _, _ ->
                    navController.navigate("main") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onAlreadyHaveAccount = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }

        composable("forgot") {
            ForgotPasswordScreen(
                onSendResetLink = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }

        composable("main") {
            MainScreen(
                navController = navController,
                onSignOut = {
                    navController.navigate("welcome") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable("plantationList") {
            PlantationListScreen(
                onBack = { navController.popBackStack() },
                onPlantClick = { plant -> navController.navigate("plantDetail/${plant.id}") }
            )
        }

        composable("plantDetail/{plantId}") { backStackEntry ->
            val plantId = backStackEntry.arguments?.getString("plantId")?.toIntOrNull()
            val plant = plantId?.let { getPlantById(it) }
            if (plant != null) {
                PlantDetailScreen(
                    plant = plant,
                    onBack = { navController.popBackStack() },
                    onUpdateStatus = { id -> navController.navigate("statusUpdate/$id") }
                )
            } else {
                navController.popBackStack()
            }
        }

        composable("newPlant") {
            NewPlantScreen(
                onBack = { navController.popBackStack() },
                onPlantLogged = {
                    navController.popBackStack()
                }
            )
        }

        composable("statusUpdate/{plantId}") { backStackEntry ->
            val plantId = backStackEntry.arguments?.getString("plantId")?.toIntOrNull() ?: 1
            val plant = getPlantById(plantId)
            StatusUpdateScreen(
                plantId = plantId,
                plantName = plant?.name ?: "Unknown Tree",
                onBack = { navController.popBackStack() },
                onSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable("speciesGuide") {
            SpeciesGuideScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable("profileSettings") {
            ProfileSettingsScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }
    }
}

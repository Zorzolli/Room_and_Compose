package com.example.room_and_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.room_and_compose.data.entities.Cities
import com.example.room_and_compose.ui.theme.Room_and_ComposeTheme
import com.example.room_and_compose.view.AlterationView
import com.example.room_and_compose.view.ListCitiesView
import com.example.room_and_compose.view.RegistrationScreenView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Room_and_ComposeTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "homeScreen") {
                        composable("homeScreen") {
                            HomeScreen(navController = navController)
                        }
                        composable("registerScreen") {
                            RegistrationScreenView(navController = navController)
                        }
                        composable("alterationScreen") {
                            val city = navController.previousBackStackEntry?.arguments?.getParcelable<Cities>("city")
                            city?.let {
                                AlterationView(navController = navController, cities = city)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Crud whit Room and Compose") })

    },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("registerScreen")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        },
        content = {
            ListCitiesView(navController = navController)
        })
}

@file:Suppress("UNREACHABLE_CODE")

package com.uvg.lab12anggelie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.safe.args.generator.models.Destination
import com.google.android.datatransport.runtime.Destination
import com.uvg.lab12anggelie.ui.theme.Lab12anggelieTheme


data class NavItem(
    val title: String,
    val destination: Any,
    val imageVector: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab12anggelieTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    rememberNavController()
        val nothing = null
        val ScreenDestination = null
        val ProfileDestination1 = null
    val items = listOf(
        ScreenDestination?.let { NavItem("Characters", it, Icons.Sharp.Person) },
        nothing?.let { NavItem("Places", it, Icons.Sharp.Place) },
        ProfileDestination1?.let { NavItem("Profile", it, Icons.Sharp.AccountBox) }
    )

    var selectedItemIndex by    rememberSaveable { mutableStateOf(0) }
    val logged by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (logged) {
                NavigationBar {
                    items.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            label = {
                                if (navItem != null) {
                                    Text(text = navItem.title)
                                }
                            },
                            onClick = {
                                selectedItemIndex = index
                                if (navItem != null) {
                                    navigate()
                                }
                            },
                            icon = {
                                if (navItem != null) {
                                    Icon(navItem.imageVector, contentDescription = "Icon")
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavHost()
    }
}

@Composable
fun NavHost() {
    Destination
}

private fun navigate() {
    TODO("Not yet implemented")
}

fun rememberNavController(): Any {
    TODO("Not yet implemented")
}


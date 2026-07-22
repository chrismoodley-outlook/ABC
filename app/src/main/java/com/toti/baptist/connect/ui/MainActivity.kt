package com.toti.baptist.connect.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toti.baptist.connect.ui.screens.auth.ForgotPasswordPage
import com.toti.baptist.connect.ui.screens.auth.SignUpPage
import com.toti.baptist.connect.ui.screens.home.HomePage
import com.toti.baptist.connect.ui.screens.login.LoginPage
import com.toti.baptist.connect.ui.screens.events.EventsPage
import com.toti.baptist.connect.ui.screens.prayer.PrayerPage
import com.toti.baptist.connect.ui.screens.connect.ConnectPage
import com.toti.baptist.connect.ui.screens.more.MorePage
import com.toti.baptist.connect.ui.screens.sermons.SermonsPage
import com.toti.baptist.connect.ui.screens.ministries.MinistriesPage
import com.toti.baptist.connect.ui.screens.profile.ProfilePage
import com.toti.baptist.connect.ui.screens.settings.SettingsPage
import com.toti.baptist.connect.ui.theme.TotiBaptistConnectTheme
import com.toti.baptist.connect.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TotiBaptistConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUser by authViewModel.currentUser.observeAsState()
    var currentRoute by remember { mutableStateOf("home") }

    // Navigate to login if user is not authenticated
    if (currentUser == null) {
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                LoginPage(navController, authViewModel)
            }
            composable("sign_up") {
                SignUpPage(navController)
            }
            composable("forgot_password") {
                ForgotPasswordPage(navController)
            }
        }
    } else {
        // User is authenticated, show main app
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onRouteChange = { route ->
                        currentRoute = route
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    // Main Tabs
                    composable("home") {
                        currentRoute = "home"
                        HomePage(navController)
                    }
                    composable("events") {
                        currentRoute = "events"
                        EventsPage(navController)
                    }
                    composable("prayer") {
                        currentRoute = "prayer"
                        PrayerPage(navController)
                    }
                    composable("connect") {
                        currentRoute = "connect"
                        ConnectPage(navController)
                    }
                    composable("more") {
                        currentRoute = "more"
                        MorePage(navController)
                    }

                    // Detail Screens (no bottom nav)
                    composable("sermons") {
                        SermonsPage(navController)
                    }
                    composable("ministries") {
                        MinistriesPage(navController)
                    }
                    composable("profile") {
                        ProfilePage(navController)
                    }
                    composable("settings") {
                        SettingsPage(navController)
                    }
                }
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val emoji: String
)

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onRouteChange: (String) -> Unit
) {
    val navItems = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home, "🏠"),
        BottomNavItem("events", "Events", Icons.Default.Event, "📅"),
        BottomNavItem("prayer", "Prayer", Icons.Default.Favorite, "🙏"),
        BottomNavItem("connect", "Connect", Icons.Default.People, "👥"),
        BottomNavItem("more", "More", Icons.Default.MoreVert, "☰")
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Text(
                        text = item.emoji,
                        fontSize = 20.sp
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    onRouteChange(item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}

package com.toti.baptist.connect.ui.screens.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.toti.baptist.connect.ui.theme.Primary

@Composable
fun MorePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("☰ More") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Account Section
            item {
                Text(
                    text = "Account",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.AccountCircle,
                    label = "My Profile",
                    onTap = { navController.navigate("profile") }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    onTap = { navController.navigate("settings") }
                )
            }

            // Church Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Church Information",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Info,
                    label = "About Church",
                    onTap = { }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.LocationOn,
                    label = "Location & Hours",
                    onTap = { }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Phone,
                    label = "Contact Us",
                    onTap = { }
                )
            }

            // Giving Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Giving",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.LocalOffer,
                    label = "Give Online",
                    onTap = { },
                    highlight = true
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Receipt,
                    label = "Giving History",
                    onTap = { }
                )
            }

            // Resources Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Resources",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Book,
                    label = "Bible Study",
                    onTap = { }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.PlayArrow,
                    label = "Media Library",
                    onTap = { navController.navigate("sermons") }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Help,
                    label = "FAQ",
                    onTap = { }
                )
            }

            // Support Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Support",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Email,
                    label = "Send Feedback",
                    onTap = { }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Share,
                    label = "Share App",
                    onTap = { }
                )
            }

            item {
                MoreMenuItem(
                    icon = Icons.Default.Info,
                    label = "About App",
                    onTap = { }
                )
            }

            // Logout
            item {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = {
                        // TODO: Implement logout
                        navController.navigate("login") {
                            popUpTo("more") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(
                        Icons.Default.Logout,
                        contentDescription = "Logout",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout")
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun MoreMenuItem(
    icon: ImageVector,
    label: String,
    onTap: () -> Unit,
    highlight: Boolean = false
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onTap,
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (highlight) Primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    icon,
                    contentDescription = label,
                    tint = if (highlight) Primary else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (highlight) Primary else MaterialTheme.colorScheme.onSurface
                )
            }
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

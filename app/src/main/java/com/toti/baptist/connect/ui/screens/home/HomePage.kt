package com.toti.baptist.connect.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.toti.baptist.connect.ui.theme.Primary
import com.toti.baptist.connect.ui.theme.Secondary
import com.toti.baptist.connect.ui.theme.Success

@Composable
fun HomePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = "Welcome Home! 👋",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            },
            actions = {
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
                IconButton(onClick = { navController.navigate("profile") }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Today's Verse Section
            item {
                TodaysVerseCard()
            }

            // Quick Navigation Buttons
            item {
                QuickNavigationGrid(navController)
            }

            // Upcoming Events Section
            item {
                SectionHeader(
                    title = "Upcoming Events",
                    icon = Icons.Default.Event,
                    onViewAll = { navController.navigate("events") }
                )
            }

            item {
                UpcomingEventCard(
                    title = "Sunday Service",
                    date = "Tomorrow, 9:00 AM",
                    location = "Main Sanctuary",
                    attendees = 145,
                    onNavigate = { navController.navigate("events") }
                )
            }

            item {
                UpcomingEventCard(
                    title = "Youth Group Meeting",
                    date = "Friday, 7:00 PM",
                    location = "Fellowship Hall",
                    attendees = 32,
                    onNavigate = { navController.navigate("events") }
                )
            }

            // Latest Sermon Section
            item {
                SectionHeader(
                    title = "Latest Sermon",
                    icon = Icons.Default.PlayArrow,
                    onViewAll = { navController.navigate("sermons") }
                )
            }

            item {
                LatestSermonCard(
                    title = "Living by Faith",
                    preacher = "Pastor John",
                    date = "Last Sunday",
                    duration = "45:30",
                    onNavigate = { navController.navigate("sermons") }
                )
            }

            // Prayer Requests Section
            item {
                SectionHeader(
                    title = "Prayer Requests",
                    icon = Icons.Default.Favorite,
                    onViewAll = { navController.navigate("prayer") }
                )
            }

            item {
                PrayerRequestCard(
                    title = "Health & Healing",
                    description = "Pray for our church members recovering from illness",
                    prayers = 23,
                    onNavigate = { navController.navigate("prayer") }
                )
            }

            // Announcements Section
            item {
                SectionHeader(
                    title = "Announcements",
                    icon = Icons.Default.NotificationsActive,
                    onViewAll = { }
                )
            }

            item {
                AnnouncementCard(
                    title = "New Bible Study Group",
                    description = "Starting this Wednesday at 7 PM. All are welcome!",
                    date = "Posted 2 days ago"
                )
            }

            item {
                AnnouncementCard(
                    title = "Community Outreach",
                    description = "Join us in serving the less fortunate in our community.",
                    date = "Posted 5 days ago"
                )
            }

            // Ministry Opportunities Section
            item {
                SectionHeader(
                    title = "Ministry Opportunities",
                    icon = Icons.Default.Groups,
                    onViewAll = { navController.navigate("ministries") }
                )
            }

            item {
                MinistryOpportunityCard(
                    ministry = "Worship Team",
                    role = "Vocalist needed",
                    description = "Join our dynamic worship team",
                    onNavigate = { navController.navigate("ministries") }
                )
            }

            item {
                MinistryOpportunityCard(
                    ministry = "Children's Ministry",
                    role = "Sunday School Teacher",
                    description = "Make a difference in children's lives",
                    onNavigate = { navController.navigate("ministries") }
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun TodaysVerseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Primary.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today's Verse",
                style = MaterialTheme.typography.labelLarge,
                color = Primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "\"For God so loved the world that he gave his one and only Son, that whoever believes in him shall not perish but have eternal life.\"",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "John 3:16",
                style = MaterialTheme.typography.labelMedium,
                color = Primary,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }
}

@Composable
fun QuickNavigationGrid(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickNavButton(
                icon = Icons.Default.Event,
                label = "Events",
                color = Primary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("events") }
            )
            QuickNavButton(
                icon = Icons.Default.Favorite,
                label = "Prayer",
                color = Secondary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("prayer") }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickNavButton(
                icon = Icons.Default.PlayArrow,
                label = "Sermons",
                color = Success,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("sermons") }
            )
            QuickNavButton(
                icon = Icons.Default.Groups,
                label = "Ministries",
                color = Primary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("ministries") }
            )
        }
    }
}

@Composable
fun QuickNavButton(
    icon: androidx.compose.material.icons.Icons,
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .height(100.dp),
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(
            containerColor = color.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier.size(32.dp),
                tint = color
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    icon: androidx.compose.material.icons.Icons,
    onViewAll: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Primary
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextButton(onClick = onViewAll) {
            Text(
                text = "View All",
                style = MaterialTheme.typography.labelMedium,
                color = Primary
            )
        }
    }
}

@Composable
fun UpcomingEventCard(
    title: String,
    date: String,
    location: String,
    attendees: Int,
    onNavigate: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onNavigate
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Primary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "👥 $attendees attending",
                style = MaterialTheme.typography.labelSmall,
                color = Primary
            )
        }
    }
}

@Composable
fun LatestSermonCard(
    title: String,
    preacher: String,
    date: String,
    duration: String,
    onNavigate: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onNavigate
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = Primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "By $preacher",
                    style = MaterialTheme.typography.labelSmall
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "📅 $date",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "⏱️ $duration",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun PrayerRequestCard(
    title: String,
    description: String,
    prayers: Int,
    onNavigate: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onNavigate
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Secondary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "🙏 $prayers people praying",
                style = MaterialTheme.typography.labelSmall,
                color = Secondary
            )
        }
    }
}

@Composable
fun AnnouncementCard(
    title: String,
    description: String,
    date: String
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun MinistryOpportunityCard(
    ministry: String,
    role: String,
    description: String,
    onNavigate: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onNavigate
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = ministry,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = role,
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary
                    )
                }
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Primary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

package com.toti.baptist.connect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class TimeZoneInfo(
    val name: String,
    val zoneId: ZoneId,
    val displayName: String
)

@Composable
fun DigitalClockScreen() {
    var currentTime by remember { mutableStateOf(System.currentTimeMillis()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = System.currentTimeMillis()
        }
    }

    val timeZones = listOf(
        TimeZoneInfo("Eastern", ZoneId.of("America/New_York"), "🗽 Eastern Time (ET)"),
        TimeZoneInfo("Central", ZoneId.of("America/Chicago"), "🏙️ Central Time (CT)"),
        TimeZoneInfo("Mountain", ZoneId.of("America/Denver"), "🏔️ Mountain Time (MT)"),
        TimeZoneInfo("Pacific", ZoneId.of("America/Los_Angeles"), "🌊 Pacific Time (PT)"),
        TimeZoneInfo("GMT", ZoneId.of("UTC"), "🌍 Greenwich Mean Time (GMT)"),
        TimeZoneInfo("CET", ZoneId.of("Europe/Paris"), "🇪🇺 Central European Time (CET)"),
        TimeZoneInfo("IST", ZoneId.of("Asia/Kolkata"), "🇮🇳 Indian Standard Time (IST)"),
        TimeZoneInfo("JST", ZoneId.of("Asia/Tokyo"), "🇯🇵 Japan Standard Time (JST)"),
        TimeZoneInfo("AEST", ZoneId.of("Australia/Sydney"), "🇦🇺 Australian Eastern Time (AEST)"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header
        Text(
            text = "⏰ Global Time Zones",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Scrollable clock list
        LazyColumnForTimeZones(timeZones, currentTime)
    }
}

@Composable
fun LazyColumnForTimeZones(timeZones: List<TimeZoneInfo>, currentTime: Long) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(androidx.compose.foundation.rememberScrollState())
    ) {
        timeZones.forEach { tz ->
            TimeZoneClockCard(tz, currentTime)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TimeZoneClockCard(timeZone: TimeZoneInfo, currentTime: Long) {
    val zonedDateTime = ZonedDateTime.now(timeZone.zoneId)
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
    
    val formattedTime = zonedDateTime.format(timeFormatter)
    val formattedDate = zonedDateTime.format(dateFormatter)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Time Zone Name
            Text(
                text = timeZone.displayName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Digital Clock Display
            Text(
                text = formattedTime,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily.Monospace,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Date Display
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.alpha(0.7f)
            )

            // UTC Offset
            Text(
                text = "UTC Offset: ${zonedDateTime.offset}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .alpha(0.6f)
            )
        }
    }
}

@Composable
fun Modifier.alpha(alpha: Float): Modifier = this.then(
    Modifier.drawWithContent {
        drawContext.canvas.nativeCanvas.saveLayer(null, null)
        drawContent()
        drawContext.canvas.nativeCanvas.restore()
    }
)

package com.example.hasiru.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hasiru.ui.theme.*

// ── Hardcoded marker positions (fraction of map width/height) ──
data class MarkerData(
    val id: Int,
    val xFraction: Float,
    val yFraction: Float,
    val color: Color,
    val label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onBack: () -> Unit = {},
    onMarkerClick: (MarkerData) -> Unit = {}
) {
    val density = LocalDensity.current
    val primaryColor = MaterialTheme.colorScheme.primary
    val leafBackground = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
    
    val sampleMarkers = listOf(
        MarkerData(1, 0.3f, 0.4f, Green40, "Alive tree"),
        MarkerData(2, 0.5f, 0.6f, Red40, "Dead tree"),
        MarkerData(3, 0.8f, 0.2f, Color.Gray, "Unknown tree"),
        MarkerData(4, 0.1f, 0.7f, Red40, "Dead tree"),
        MarkerData(5, 0.65f, 0.85f, Green40, "Alive tree"),
        MarkerData(6, 0.45f, 0.25f, Green40, "Alive tree"),
        MarkerData(7, 0.7f, 0.5f, Color.Gray, "Unknown tree"),
    )

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // ── 1. Map Canvas ──
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(leafBackground)
            ) {
                val mapWidthPx = constraints.maxWidth.toFloat()
                val mapHeightPx = constraints.maxHeight.toFloat()

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height

                    // Ultra-soft grid lines
                    for (i in 0..10) {
                        val x = i * (width / 10)
                        drawLine(
                            color = primaryColor.copy(alpha = 0.05f),
                            start = Offset(x, 0f),
                            end = Offset(x, height),
                            strokeWidth = 1f
                        )
                        val y = i * (height / 10)
                        drawLine(
                            color = primaryColor.copy(alpha = 0.05f),
                            start = Offset(0f, y),
                            end = Offset(width, y),
                            strokeWidth = 1f
                        )
                    }
                }

                // ── 2. Tree Markers ──
                sampleMarkers.forEach { marker ->
                    val px = mapWidthPx * marker.xFraction
                    val py = mapHeightPx * marker.yFraction
                    val iconSizeDp = 36.dp
                    val iconSizePx = with(density) { iconSizeDp.toPx() }

                    Surface(
                        modifier = Modifier
                            .offset(
                                x = with(density) { (px - iconSizePx / 2).toDp() },
                                y = with(density) { (py - iconSizePx / 2).toDp() }
                            )
                            .size(iconSizeDp),
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 4.dp,
                        onClick = { onMarkerClick(marker) }
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxSize()
                                .background(marker.color, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Park,
                                contentDescription = marker.label,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // ── 3. Floating Search Bar ──
            Surface(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                    Text(
                        "Search Bangalore...",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.padding(end = 16.dp),
                        tint = primaryColor
                    )
                }
            }

            // ── 4. Filter Chips ──
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 80.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MapFilterChip("All", true)
                MapFilterChip("Alive", false)
                MapFilterChip("Dead", false)
            }

            // ── 6. Modern Bottom Card ──
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Handle bar
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray.copy(alpha = 0.5f))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "PLANTATION WATCH",
                        style = MaterialTheme.typography.labelMedium,
                        color = primaryColor,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "High Vitality Area",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Plantations in this region show 94% survival rate. Keep up the good work!",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        Text("View Region Details", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun MapFilterChip(label: String, isSelected: Boolean) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
        shadowElevation = 2.dp,
        modifier = Modifier.clickable { }
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) Color.White else Color.Black,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    HasiruTheme {
        MapScreen()
    }
}

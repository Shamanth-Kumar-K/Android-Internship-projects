package com.example.hasiru.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hasiru.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusUpdateScreen(
    plantId: Int,
    plantName: String = "Tree",
    onBack: () -> Unit = {},
    onSaved: () -> Unit = {}
) {
    var selectedStatus by remember { mutableStateOf<PlantStatus?>(null) }
    var photoTaken by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Update Status", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ── Tree name header ──
            Text(
                plantName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))

            // ── Status selection: three radio-like cards ──
            Text(
                "Current Status",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatusOption(
                    label = "Alive",
                    color = Green40,
                    icon = Icons.Filled.CheckCircle,
                    selected = selectedStatus == PlantStatus.ALIVE,
                    onClick = { selectedStatus = PlantStatus.ALIVE },
                    modifier = Modifier.weight(1f)
                )
                StatusOption(
                    label = "Dead",
                    color = Red40,
                    icon = Icons.Filled.Cancel,
                    selected = selectedStatus == PlantStatus.DEAD,
                    onClick = { selectedStatus = PlantStatus.DEAD },
                    modifier = Modifier.weight(1f)
                )
                StatusOption(
                    label = "Unknown",
                    color = Color.Gray,
                    icon = Icons.Filled.Help,
                    selected = selectedStatus == PlantStatus.UNKNOWN,
                    onClick = { selectedStatus = PlantStatus.UNKNOWN },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ── Growth photo capture (mock) ──
            Text(
                "Growth Photo",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        2.dp,
                        if (photoTaken) Green40 else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { photoTaken = true },
                contentAlignment = Alignment.Center
            ) {
                if (!photoTaken) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.AddAPhoto, contentDescription = null, tint = Green40, modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Tap to capture growth photo", color = Green40)
                    }
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Park, contentDescription = null, tint = Green40, modifier = Modifier.size(64.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("New photo captured!", color = Green40, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // ── Save Update button ──
            Button(
                onClick = onSaved,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = selectedStatus != null && photoTaken
            ) {
                Text("Save Update", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StatusOption(
    label: String,
    color: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) color.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (selected) 4.dp else 1.dp),
        border = if (selected) androidx.compose.foundation.BorderStroke(2.dp, color) else null
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, fontWeight = FontWeight.Bold, color = color, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusUpdateScreenPreview() {
    HasiruTheme {
        StatusUpdateScreen(
            plantId = 1,
            plantName = "Neem Tree"
        )
    }
}

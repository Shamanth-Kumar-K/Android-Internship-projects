package com.example.hasiru.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hasiru.R
import com.example.hasiru.ui.theme.*
import kotlinx.coroutines.launch

// Use Badge from Models.kt, no need to define it again.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSpeciesGuide: () -> Unit = {},
    onSettings: () -> Unit = {},
    onSignOut: () -> Unit = {},
    onBack: () -> Unit = {},
    onEditProfilePicture: () -> Unit = {},
    onEditName: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    val userName = "Kshamanth Kumar"
    val badgeTitle = "PIONEER ECO-GUARDIAN"
    val treesPlanted = 4
    val areasCovered = 3
    val nextLevel = "Sapling"
    val requiredForNext = 5
    val progress = treesPlanted.toFloat() / requiredForNext

    val badges = listOf(
        Badge("Pioneer", Icons.Filled.EmojiNature, true),
        Badge("Sprout", Icons.Filled.Park, true),
        Badge("Sapling", Icons.Filled.Forest, false),
        Badge("Oak", Icons.Filled.Nature, false),
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Surface(
                        onClick = onBack,
                        shape = CircleShape,
                        color = Color.Black.copy(alpha = 0.05f),
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Back",
                            modifier = Modifier.padding(10.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            // Header card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Green40),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Green40, Green20)
                            )
                        )
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.BottomEnd) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f))
                                    .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                                    .clickable { onEditProfilePicture() },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Filled.Person,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                            Surface(
                                onClick = onEditProfilePicture,
                                shape = CircleShape,
                                color = Amber40,
                                modifier = Modifier
                                    .size(32.dp)
                                    .border(2.dp, Green40, CircleShape)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.camera_icon),
                                    contentDescription = "Edit Profile Picture",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .size(16.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { onEditName() }
                        ) {
                            Text(
                                userName,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.edit_icon),
                                contentDescription = "Edit Name",
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            color = Color.Black.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(
                                badgeTitle,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Growth Journey
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "GROWTH JOURNEY",
                            style = MaterialTheme.typography.labelSmall,
                            color = Green40,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                        Icon(
                            Icons.AutoMirrored.Filled.TrendingUp,
                            null,
                            tint = Green40,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Level Up to $nextLevel",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            "$treesPlanted",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Green40
                        )
                        Text(
                            " / $requiredForNext trees planted",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // Custom Progress Bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(CircleShape)
                            .background(Green80.copy(alpha = 0.2f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .fillMaxHeight()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Amber80, Amber40)
                                    )
                                )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Plant ${requiredForNext - treesPlanted} more tree${if (requiredForNext - treesPlanted > 1) "s" else ""} to reach next milestone!",
                        style = MaterialTheme.typography.bodySmall,
                        color = Green40,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.Park,
                    label = "TREES PLANTED",
                    value = "$treesPlanted",
                    color = Green40
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.LocationCity,
                    label = "CITY AREAS",
                    value = "$areasCovered",
                    color = Brown40
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "UNLOCKED MILESTONES",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    "SEE ALL",
                    style = MaterialTheme.typography.labelMedium,
                    color = Green40,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(badges) { badge ->
                    BadgeCard(badge = badge, onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Milestone: ${badge.name}")
                        }
                    })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "ACCOUNT & IMPACT",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SettingsRow(label = "Profile Settings", icon = Icons.Filled.Settings, onClick = onSettings)
                SettingsRow(label = "Conservation Reports", icon = Icons.Filled.Description, onClick = onSpeciesGuide)
                SettingsRow(label = "Plantation Records", icon = Icons.AutoMirrored.Filled.List, onClick = {})
                SettingsRow(label = "Activity Logs", icon = Icons.Filled.History, onClick = {})
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = onSignOut,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Red40),
                border = BorderStroke(1.dp, Red40.copy(alpha = 0.5f))
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sign Out of Namma-Hasiru", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    value: String,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = color, modifier = Modifier.size(26.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 36.sp, color = color)
            Text(
                label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun BadgeCard(badge: Badge, onClick: () -> Unit = {}) {
    val isAchieved = badge.achieved
    val accentColor = if (isAchieved) Amber40 else Color.Gray.copy(alpha = 0.4f)
    
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(105.dp)
            .height(135.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isAchieved) Color.White else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isAchieved) 6.dp else 0.dp
        ),
        border = if (isAchieved) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (!isAchieved) {
                Icon(
                    Icons.Default.Lock,
                    null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(14.dp),
                    tint = Color.Gray.copy(alpha = 0.4f)
                )
            }
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(
                            if (isAchieved) accentColor.copy(alpha = 0.15f)
                            else Color.Gray.copy(alpha = 0.1f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        badge.icon,
                        null,
                        tint = accentColor,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    badge.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (isAchieved) MaterialTheme.colorScheme.onSurface else Color.Gray,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun SettingsRow(label: String, icon: ImageVector, onClick: () -> Unit) {
    Surface(onClick = onClick, shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surface) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(label, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Icon(Icons.Filled.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    HasiruTheme { ProfileScreen() }
}
package com.example.hasiru.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hasiru.ui.theme.*

data class PlantEntry(
    val id: Int,
    val name: String,
    val scientificName: String,
    val date: String,
    val location: String,
    val status: PlantStatus
)

enum class PlantStatus(val label: String, val color: Color) {
    ALIVE("Alive", Green40),
    DEAD("Dead", Red40),
    UNKNOWN("Unknown", Color.Gray)
}

data class GrowthUpdate(
    val date: String,
    val status: PlantStatus,
    val description: String
)

data class Badge(
    val name: String,
    val icon: ImageVector,
    val achieved: Boolean
)

data class SettingItem(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

val allPlants = listOf(
    PlantEntry(1, "Neem Tree", "Azadirachta indica", "15 Mar 2024", "Area 4 • Bangalore", PlantStatus.ALIVE),
    PlantEntry(2, "Peepal Tree", "Ficus religiosa", "20 Feb 2024", "Bangalore", PlantStatus.DEAD),
    PlantEntry(3, "Banyan Tree", "Ficus benghalensis", "10 Jan 2024", "Bangalore", PlantStatus.UNKNOWN),
    PlantEntry(4, "Ashoka Tree", "Polyalthia longifolia", "5 Dec 2023", "12.97° N, 77.5° E", PlantStatus.DEAD),
    PlantEntry(5, "Mango Tree", "Mangifera indica", "1 Jan 2024", "Lalbagh, Bangalore", PlantStatus.ALIVE),
    PlantEntry(6, "Teak", "Tectona grandis", "18 Apr 2024", "Cubbon Park", PlantStatus.ALIVE),
    PlantEntry(7, "Jamun", "Syzygium cumini", "22 Mar 2024", "Jayanagar", PlantStatus.UNKNOWN),
)

fun getPlantById(id: Int): PlantEntry? = allPlants.find { it.id == id }

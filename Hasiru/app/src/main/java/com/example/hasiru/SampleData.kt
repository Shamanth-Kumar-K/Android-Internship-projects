package com.example.hasiru

import com.example.hasiru.screens.PlantEntry
import com.example.hasiru.screens.PlantStatus

val samplePlantList = listOf(
    PlantEntry(1, "Neem Tree", "Azadirachta indica", "15 Mar 2024", "12.97° N, 77.5° E", PlantStatus.ALIVE),
    PlantEntry(2, "Peepal Tree", "Ficus religiosa", "20 Feb 2024", "12.95° N, 77.5° E", PlantStatus.DEAD),
    PlantEntry(3, "Banyan Tree", "Ficus benghalensis", "10 Jan 2024", "12.98° N, 77.5° E", PlantStatus.UNKNOWN),
    PlantEntry(4, "Ashoka Tree", "Polyalthia longifolia", "5 Dec 2023", "12.97° N, 77.5° E", PlantStatus.DEAD),
    PlantEntry(5, "Mango Tree", "Mangifera indica", "1 Jan 2024", "Lalbagh, Bangalore", PlantStatus.ALIVE),
    PlantEntry(6, "Teak", "Tectona grandis", "18 Apr 2024", "Cubbon Park", PlantStatus.ALIVE),
    PlantEntry(7, "Jamun", "Syzygium cumini", "22 Mar 2024", "Jayanagar", PlantStatus.UNKNOWN)
)

fun getPlantById(id: Int): PlantEntry? = samplePlantList.find { it.id == id }
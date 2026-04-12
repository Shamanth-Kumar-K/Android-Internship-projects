package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Artwork(
    @DrawableRes val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(R.drawable.hampi, "Hampi Stone Chariot", "Vijayanagara Empire", "14th Century"),
        Artwork(R.drawable.dasara, "Mysuru Dasara", "Mysuru Royal Family", "1610"),
        Artwork(R.drawable.palace, "Mysore Palace", "Ambavilasa", "Mysuru"),
        Artwork(R.drawable.badamicaves, "Badami Cave Temples", "Chalukya Dynasty", "6th Century"),
        Artwork(R.drawable.srikrishnadevaraya, "Sri Krishnadevaraya", "Vijayanagara Empire", "1509-1529"),
        Artwork(R.drawable.shivanasamudra_waterfalls, "Shivanasamudra Falls", "water Falls", "karnataka")
    )

    var currentIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Artwork Image Display
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shadowElevation = 8.dp,
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageResourceId),
                contentDescription = currentArtwork.title,
                modifier = Modifier.padding(32.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Artwork Info Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFECECEC),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = currentArtwork.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.headlineMedium
                )
                Row {
                    Text(
                        text = currentArtwork.artist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = " (${currentArtwork.year})",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = {
                    currentIndex = (currentIndex + 1) % artworks.size
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}

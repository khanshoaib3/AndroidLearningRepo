package com.github.khanshoaib3.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.khanshoaib3.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkRow(Modifier.fillMaxWidth())
        Spacer(Modifier.height(128.dp))
        ArtworkDescriptionRow(Modifier.fillMaxWidth())
        Spacer(Modifier.height(32.dp))
        NavigationButtonsRow(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Bottom)
        )
    }
}

@Composable
fun ArtworkRow(modifier: Modifier = Modifier) {
    Surface(modifier, color = Color.White, shadowElevation = 16.dp) {
        Row(
            modifier = modifier.padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.artwork_1), contentDescription = "Artwork 1")
        }
    }
}

@Composable
fun ArtworkDescriptionRow(modifier: Modifier = Modifier) {
    Surface(modifier, color = Color(212, 230, 241), shadowElevation = 8.dp) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            Column {
                Text(
                    "Komrade, Meme is public property. No need steal...is yours.",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 32.dp)
                )
                Text(
                    "Reddit (2024)",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun NavigationButtonsRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {}, modifier = Modifier) {
            Text(
                stringResource(R.string.prev_button),
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp
            )
        }
        Button(onClick = {}, modifier = Modifier) {
            Text(
                stringResource(R.string.next_button),
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout(modifier = Modifier.fillMaxWidth())
    }
}
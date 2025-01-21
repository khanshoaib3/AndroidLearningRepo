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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.github.khanshoaib3.artspace.ui.theme.ArtSpaceTheme
import kotlin.math.max
import kotlin.math.min

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

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var artworkIndex by remember { mutableIntStateOf(0) }
    val view = LocalView.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkRow(artworkIndex, Modifier.fillMaxWidth())
        Spacer(Modifier.height(128.dp))
        ArtworkDescriptionRow(artworkIndex, Modifier.fillMaxWidth())
        Spacer(Modifier.height(32.dp))
        NavigationButtonsRow(
            previousOnClick = {
                artworkIndex = max(0, artworkIndex - 1)
                view.performHapticFeedback(HapticFeedbackConstantsCompat.CONFIRM)
            },
            nextOnClick = {
                artworkIndex = min(8, artworkIndex + 1)
                view.performHapticFeedback(HapticFeedbackConstantsCompat.CONFIRM)
            },
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Bottom)
        )
    }
}

@Composable
fun ArtworkRow(artworkIndex: Int, modifier: Modifier = Modifier) {
    Surface(modifier, color = Color.White, shadowElevation = 16.dp) {
        Row(
            modifier = modifier.padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(getArtworkFromIndex(artworkIndex)),
                contentDescription = getArtworkContentDescriptionFromIndex(artworkIndex),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(340.dp)
            )
        }
    }
}

@Composable
fun ArtworkDescriptionRow(artworkIndex: Int, modifier: Modifier = Modifier) {
    Surface(modifier, color = Color(212, 230, 241), shadowElevation = 8.dp) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            Column {
                Text(
                    getArtworkContentTitleFromIndex(artworkIndex),
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 32.dp)
                )
                Text(
                    getArtworkContentAuthorFromIndex(artworkIndex),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun NavigationButtonsRow(
    previousOnClick: () -> Unit,
    nextOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = previousOnClick, modifier = Modifier) {
            Text(
                stringResource(R.string.prev_button),
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp
            )
        }
        Button(onClick = nextOnClick, modifier = Modifier) {
            Text(
                stringResource(R.string.next_button),
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp
            )
        }
    }
}

private fun getArtworkFromIndex(index: Int): Int = when (index) {
    0 -> R.drawable.artwork_0
    1 -> R.drawable.artwork_1
    2 -> R.drawable.artwork_2
    3 -> R.drawable.artwork_3
    4 -> R.drawable.artwork_4
    5 -> R.drawable.artwork_5
    6 -> R.drawable.artwork_6
    7 -> R.drawable.artwork_7
    8 -> R.drawable.artwork_8
    else -> R.drawable.artwork_0
}

private fun getArtworkContentDescriptionFromIndex(index: Int): String = when (index) {
    0 -> "Robber cutting a hole in the roof saying, \"Don't mind me, I'm just stealing the meme above.\""
    1 -> "Staling saying, \"Komrade, meme is public property. No need steal. Is yours.\""
    2 -> "Polar bear (as any other plant) vs fox (potatoes). Polar bear: water, soil, temperature, basically everything needs to be perfect. Fox: Dirt (optional)"
    3 -> "A Hollow Knight tattoo drawn on the person's hand. Hollow Knight is standing with his shade standing behind him."
    4 -> "Hollow Knight artwork with dreamers' masks."
    5 -> "A Max Payne poster drawn on the wall of a cement hut."
    6 -> "\"Please do not the cat\" written with a person reaching to grab the cat's mouth. \"Me when I the cat\" written with the picture of a nuclear holocaust."
    7 -> ""
    8 -> ""
    else -> ""
}

private fun getArtworkContentTitleFromIndex(index: Int): String = when (index) {
    0 -> "Meme stealing"
    1 -> "Pardoned by Stalin"
    2 -> "Chad Potato"
    3 -> "Hollow Knight tattoo"
    4 -> "Hollow Knight Artwork"
    5 -> "Max's Hut"
    6 -> "Do not the cat!"
    7 -> "Poor wikipedia"
    8 -> "Mom...noooooooo (╥﹏╥)"
    else -> "(╥﹏╥)"
}

private fun getArtworkContentAuthorFromIndex(index: Int): String = when (index) {
    0 -> "Reddit (2024)"
    1 -> "Reddit (2024)"
    2 -> "r/memes"
    3 -> "r/HollowKnight"
    4 -> "u/Ok_Geologist316"
    5 -> "u/dear_bears"
    6 -> "r/memes"
    7 -> "u/Ghassen_Xd78"
    8 -> "u/Kuky55"
    else -> "(╥﹏╥)"
}
package com.github.khanshoaib3.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.khanshoaib3.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeMailLayout(modifier: Modifier = Modifier) {
    // Import getValue and setValue to fix error
    var step by remember { mutableIntStateOf(0) }
    val image = when (step) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        3 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val caption = when (step) {
        0 -> R.string.lemon_tree
        1 -> R.string.lemon
        2 -> R.string.glass_o_lemonade
        3 -> R.string.empty_glass
        else -> R.string.lemon_tree
    }
    val contentDescription = when (step) {
        0 -> "Lemon Tree"
        1 -> "Lemon"
        2 -> "Glass o Lemonade"
        3 -> "Empty Glass"
        else -> "Lemon Tree"
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val backgroundColor = Color(red = 211, green = 232, blue = 219)
        val backgroundShape = RoundedCornerShape(size = 14.dp)
        Image(
            painter = painterResource(image),
            contentDescription = contentDescription,
            modifier = Modifier
                .clickable(enabled = true, onClick = { step = (step + 1) % 4 })
                .border(width = 2.dp, color = backgroundColor, shape = backgroundShape)
                .background(color = backgroundColor, shape = backgroundShape)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Text(stringResource(caption))
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeMailLayout(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    }
}
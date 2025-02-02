package com.github.khanshoaib3.superheroes

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.khanshoaib3.superheroes.model.Hero
import com.github.khanshoaib3.superheroes.model.HeroesRepository
import com.github.khanshoaib3.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { SuperheroesAppTopBar() }
                ) { innerPadding ->
                    SuperheroesApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SuperheroesAppPreview() {
    SuperheroesTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { SuperheroesAppTopBar() }
        ) { innerPadding ->
            SuperheroesApp(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun SuperheroesApp(modifier: Modifier = Modifier) {
    HeroesList(HeroesRepository.heroes, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesAppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}
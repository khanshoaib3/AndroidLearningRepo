package com.github.khanshoaib3.superheroes

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.khanshoaib3.superheroes.model.Hero
import com.github.khanshoaib3.superheroes.model.HeroesRepository
import com.github.khanshoaib3.superheroes.ui.theme.SuperheroesTheme

@Preview("Light Mode", showBackground = true)
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HeroesListPreview() {
    SuperheroesTheme {
        HeroesList(HeroesRepository.heroes)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedHeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(heroes) { index, hero ->
                HeroListItem(
                    hero = hero,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun HeroesList(heroes: List<Hero>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(heroes) { hero ->
            HeroListItem(hero, Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroListItemPreview() {
    SuperheroesTheme {
        HeroListItem(
            Hero(
                nameRes = R.string.hero1,
                descriptionRes = R.string.description1,
                imageRes = R.drawable.android_superhero1
            )
        )
    }
}

@Composable
fun HeroListItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 72.dp)
                .padding(16.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(shape = MaterialTheme.shapes.small)
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = stringResource(hero.nameRes),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}
package com.github.khanshoaib3.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.khanshoaib3.gridapp.data.DataSource
import com.github.khanshoaib3.gridapp.model.Topic
import com.github.khanshoaib3.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun GridAppPreview() {
    GridApp()
}

@Composable
fun GridApp(modifier: Modifier = Modifier) {
    var topicsList = mutableListOf<Topic>()
    topicsList.addAll(DataSource.topics)
    topicsList.addAll(DataSource.topics)
    topicsList.addAll(DataSource.topics)
    Surface(modifier = modifier) {
        TopicsList(topicsList)
    }
}

@Composable
fun TopicsList(topics: List<Topic>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topics) { topic ->
            TopicItem(topic)
        }
    }
}

@Preview
@Composable
fun TopicItemPreview() {
    TopicItem(Topic(R.string.photography, 58, R.drawable.photography))
}

@Composable
fun TopicItem(topic: Topic, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .height(68.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row {
            Image(
                painter = painterResource(topic.image),
                contentDescription = stringResource(topic.title),
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp)
            ) {
                Text(stringResource(topic.title), style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.grain),
                        contentDescription = "ehh"
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("${topic.count}", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
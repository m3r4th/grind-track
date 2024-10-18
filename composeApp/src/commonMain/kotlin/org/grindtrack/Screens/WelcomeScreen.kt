package org.grindtrack.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.grindtrack.data.sampleWorkoutTemplates
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

const val STONKS_MAN_URL = "https://i.insider.com/601448566dfbe10018e00c5d"

data object WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        Welcome(Modifier)
    }
}

@Composable
fun Welcome(modifier: Modifier) {

    val statsAvailable = false

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // If no stats have been collected, then use a default image.
            // And below, add a simple message saying 'No stats available yet'.
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text("Lvl. 1")
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (statsAvailable) {
                    // TODO: draw the chart
                } else {
                    KamelImage(
                        resource = asyncPainterResource(data = STONKS_MAN_URL),
                        contentDescription = "Stonks Man Placeholder",
                        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Move your 🍑 brother!",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth() // Fill the width for centering
                    )
                }
            }

            val navigator = LocalNavigator.currentOrThrow

            // TODO replace example with real data
            val buttonScreens = listOf(WorkoutTemplateOverviewScreen(sampleWorkoutTemplates))
            val buttonTexts = listOf("Workout Templates", "Workout Tracker")

            // Under this part, we add a grid with four squared button,
            // with rounded corners.
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp), // Optional: Add padding around the grid
                verticalArrangement = Arrangement.spacedBy(8.dp), // Spacing between rows
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(4) {
                    Box(
                        modifier = Modifier.aspectRatio(1f)
                    ) {
                        Button(
                            onClick = {
                                if (it < buttonScreens.size) {
                                    navigator.push(buttonScreens[it])
                                }
                            },
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            when {
                                it < buttonTexts.size -> Text(buttonTexts[it])
                                else -> Text("Click me!")
                            }
                        }
                    }
                }
            }
        }
    }
}

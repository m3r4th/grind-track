package com.jetbrains.kmpapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.WorkoutTemplate
import com.jetbrains.kmpapp.screens.reusable.AddNewItemCard
import com.jetbrains.kmpapp.ui.AddWorkoutTemplateScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

data class WorkoutTemplateOverviewScreen(var workoutTemplate: MutableList<WorkoutTemplate>) : Screen {
    @Preview
    @Composable
    override fun Content() {
        WorkoutTemplateOverview(workoutTemplate)
    }
}

@Preview
@Composable
fun WorkoutTemplateOverview(
    workoutTemplates: MutableList<WorkoutTemplate>,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .padding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal).asPaddingValues()),
        contentPadding = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical).asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(workoutTemplates.size) { index ->
            WorkoutTemplateCard(workoutTemplates[index])
        }
        item {
            AddNewItemCard {
                navigator.push(AddWorkoutTemplateScreen(workoutTemplates))
            }
        }
    }
}

@Composable
@Preview
fun WorkoutTemplateCard(workoutTemplate: WorkoutTemplate) {
    val navigator = LocalNavigator.currentOrThrow
    val modifier = Modifier.border(1.dp, Color.Black).clickable(onClick = { navigator.push(WorkoutTemplateDetailsScreen(workoutTemplate)) })
    Row(modifier.height(IntrinsicSize.Min).fillMaxWidth()) {
        // TODO: maybe we can add an image preview here?
        Column {
            Text(text = workoutTemplate.name, style = MaterialTheme.typography.h6)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = workoutTemplate.scheduledWeekDay.name)
        }
    }
}

package com.jetbrains.kmpapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
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
import com.jetbrains.kmpapp.data.Exercise
import com.jetbrains.kmpapp.data.WorkoutTemplate
import com.jetbrains.kmpapp.screens.reusable.AddNewItemCard
import org.jetbrains.compose.ui.tooling.preview.Preview

data class WorkoutTemplateDetailsScreen(var workoutTemplate: WorkoutTemplate) : Screen {
    @Composable
    override fun Content() {
        WorkoutTemplateDetails(workoutTemplate)
    }
}

@Composable
fun WorkoutTemplateDetails(
    workoutTemplate: WorkoutTemplate,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    // TODO show scheduled time at top or bottom of screen
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .padding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal).asPaddingValues()),
        contentPadding = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical).asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val exercises = workoutTemplate.exercises
        items(exercises.size) { index ->
            ExerciseTemplateCard(exercises[index])
        }
        item {
            AddNewItemCard {
                navigator.push(AddExerciseScreen(workoutTemplate))
            }
        }
    }
}

@Preview
@Composable
fun ExerciseTemplateCard(exercise: Exercise) {
    Row(
        Modifier.height(IntrinsicSize.Min).fillMaxWidth().border(1.dp, Color.Black)
    ) {
        // TODO: maybe we can add an image preview here?
        Column {
            Text(text = exercise.name, style = MaterialTheme.typography.h6)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "${exercise.sets} Sets")
            Spacer(modifier = Modifier.width(5.dp))
            Divider(
                color = Color.Gray,
                modifier = Modifier.width(1.dp).fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "${exercise.breakSeconds}s break")
        }
    }
}

package com.jetbrains.kmpapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.Exercise
import com.jetbrains.kmpapp.data.WorkoutTemplate
import org.jetbrains.compose.ui.tooling.preview.Preview

data class AddExerciseScreen(val workoutTemplate: WorkoutTemplate) : Screen {
    @Composable
    override fun Content() {
        AddExercise(workoutTemplate)
    }
}

@Preview
@Composable
fun AddExercise(workoutTemplate: WorkoutTemplate) {
    val navigator = LocalNavigator.currentOrThrow
    ExerciseForm(onSubmit = { exercise ->
        workoutTemplate.exercises.add(exercise)
        navigator.pop()
    })
}

@Composable
fun ExerciseForm(onSubmit: (Exercise) -> Unit) {
    var name by remember { mutableStateOf("") }
    var sets by remember { mutableStateOf("") }
    var repetitions by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var breakSeconds by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = sets,
            onValueChange = { sets = it },
            label = { Text("Sets") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = repetitions,
            onValueChange = { repetitions = it },
            label = { Text("Repetitions (optional)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Time (optional)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = breakSeconds,
            onValueChange = { breakSeconds = it },
            label = { Text("Break Seconds") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val exercise = Exercise(
                name = name,
                sets = sets.toIntOrNull() ?: 0,
                repetitions = repetitions.toIntOrNull(),
                time = time.toIntOrNull(),
                breakSeconds = breakSeconds.toIntOrNull() ?: 0
            )
            onSubmit(exercise)
        }) {
            Text(text = "Submit")
        }
    }
}

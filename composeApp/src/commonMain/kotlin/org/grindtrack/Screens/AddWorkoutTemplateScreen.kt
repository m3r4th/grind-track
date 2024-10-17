package com.jetbrains.kmpapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.WorkoutTemplate
import kotlinx.datetime.DayOfWeek
import org.grindtrack.Screens.ReuseableElements.WarningDialog

data class AddWorkoutTemplateScreen(val workoutTemplates: MutableList<WorkoutTemplate>) : Screen {
    @Composable
    override fun Content() {
        AddWorkoutTemplate(workoutTemplates)
    }
}

@Composable
fun AddWorkoutTemplate(workoutTemplates: MutableList<WorkoutTemplate>) {
    val navigator = LocalNavigator.currentOrThrow
    WorkoutTemplateForm(onSubmit = { workoutTemplate ->
        workoutTemplates.add(workoutTemplate)
        navigator.pop()
    })
}

@Composable
fun WorkoutTemplateForm(onSubmit: (WorkoutTemplate) -> Unit) {
    var name by remember { mutableStateOf("") }
    var scheduledWeekDay by remember { mutableStateOf(DayOfWeek.MONDAY) }
    var scheduledTime by remember { mutableStateOf("") }

    var showWarning by remember { mutableStateOf(false) }

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
        DropDownWeekDayPicker(
            selectedWeekDay = scheduledWeekDay,
            onWeekDaySelected = { scheduledWeekDay = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = scheduledTime,
            onValueChange = { scheduledTime = it },
            label = { Text("Scheduled Time (optional, e.g., 18:30)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (name.isBlank()) {
                showWarning = true
                return@Button
            }
            val workoutTemplate = WorkoutTemplate(
                name = name,
                scheduledWeekDay = scheduledWeekDay,
                scheduledTime = scheduledTime.ifBlank { null }
            )
            onSubmit(workoutTemplate)
        }) {
            Text(text = "Submit")
        }
        if (showWarning) {
            WarningDialog(
                "Warning",
                "Please enter a name for the workout template."
            ) { showWarning = false }
        }
    }
}

@Composable
fun DropDownWeekDayPicker(
    selectedWeekDay: DayOfWeek,
    onWeekDaySelected: (DayOfWeek) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedWeekDay.name)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DayOfWeek.values().forEach { weekDay ->
                DropdownMenuItem(
                    onClick = {
                        onWeekDaySelected(weekDay)
                        expanded = false
                    }
                ) {
                    Text(text = weekDay.name)
                }
            }
        }
    }
}

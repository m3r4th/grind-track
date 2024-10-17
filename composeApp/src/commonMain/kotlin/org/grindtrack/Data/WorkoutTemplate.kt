package com.jetbrains.kmpapp.data

import kotlinx.datetime.DayOfWeek

data class WorkoutTemplate(
    val name: String,
    // TODO: Allow to schedule on multiple days + times
    val scheduledWeekDay: DayOfWeek,
    // TODO: Find good Type to repesent a simple local time like 18:30
    val scheduledTime: String? = null,
    val exercises: MutableList<Exercise> = mutableListOf()
)

val sampleWorkoutTemplates = mutableListOf(
    WorkoutTemplate(
        "Push", DayOfWeek.MONDAY, exercises = mutableListOf(
            Exercise(name = "Push-ups", sets = 3, breakSeconds = 70),
            Exercise(name = "Plank", sets = 2, breakSeconds = 5),
            Exercise(name = "Squats", sets = 4, breakSeconds = 120)
        )
    ),
    WorkoutTemplate(
        "Pull", DayOfWeek.TUESDAY, exercises = mutableListOf(
            Exercise(name = "Push-ups", sets = 3, breakSeconds = 70),
            Exercise(name = "Plank", sets = 2, breakSeconds = 5),
            Exercise(name = "Squats", sets = 4, breakSeconds = 120)
        )
    )
)

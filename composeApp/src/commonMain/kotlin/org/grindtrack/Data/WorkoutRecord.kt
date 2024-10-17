package com.jetbrains.kmpapp.data

import kotlinx.datetime.LocalDateTime

data class WorkoutRecord(
    var templateName: String,
    var timePerformed: LocalDateTime,
    val exercises: MutableList<ExerciseRecord> = mutableListOf()
)

data class ExerciseRecord(
    var exerciseName: String,
    var sets: MutableList<SetRecord> = mutableListOf()
)

data class SetRecord(
    var weight: Int? = 0,
    var reps: Int? = 0,
    var timeSeconds: Int? = 0,
)

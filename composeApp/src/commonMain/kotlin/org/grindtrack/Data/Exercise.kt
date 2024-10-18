package org.grindtrack.data

data class Exercise(
    val name: String,
    val sets: Int,
    val repetitions: Int? = null,
    val time: Int? = null,
    val breakSeconds: Int,
)
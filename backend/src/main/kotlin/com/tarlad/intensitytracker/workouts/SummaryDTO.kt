package com.tarlad.intensitytracker.workouts

class SummaryDTO(
    val workoutCount: Int,
    val totalAscent: Int,
    val totalDescent: Int,
    val totalTime: Int,
    val timeEasy: Int,
    val timeHard: Int,
    val totalDistance: Double,
    val totalCalories: Int,
)
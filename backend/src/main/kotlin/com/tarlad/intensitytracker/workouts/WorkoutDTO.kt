package com.tarlad.intensitytracker.workouts

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class WorkoutDTO (
    val activityType: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val date: LocalDateTime,

    val title: String,
    val distance: Double,
    val calories: Int,
    val time: Int,
    val avgHr: Int,
    val maxHr: Int,
    val aerobicTE: Double,
    val avgRunCadence: Int,
    val maxRunCadence: Int,
    val avgPace: Int,
    val bestPace: Int,
    val totalAscent: Int,
    val totalDescent: Int,
    val avgStrideLength: Double,
    val avgVerticalRatio: Double,
    val avgVerticalOscillation: Double,
    val avgGroundContactTime: Int,
    val avgGCTBalance: String,
    val minTemp: Double,
    val timeZ1: Int,
    val timeZ2: Int,
    val timeZ3: Int,
    val timeZ4: Int,
    val timeZ5: Int
)
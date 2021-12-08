package com.tarlad.intensitytracker.workouts

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {

    private val workoutCSVParser = WorkoutCSVParser()

    fun bulkInputWorkoutsFromCSV(csv: String): MutableList<Workout> {
        val workouts = workoutCSVParser.parseCsv(csv)

        return workoutRepository.insert(workouts)
    }

    fun getSummary(from: LocalDateTime, to: LocalDateTime): SummaryDTO {
        val workouts = workoutRepository.findWorkoutByDateBetween(from, to)


        return SummaryDTO(
            workouts.size,
            workouts.fold ( 0) { acc, curr -> acc + curr.totalAscent },
            workouts.fold ( 0) { acc, curr -> acc + curr.totalDescent },
            workouts.fold ( 0) { acc, curr -> acc + curr.time },
            workouts.fold ( 0) { acc, curr -> acc + (curr.timeZ1 + curr.timeZ2) },
            workouts.fold ( 0) { acc, curr -> acc + (curr.timeZ3 + curr.timeZ4 + curr.timeZ5) },
            workouts.fold ( 0.0) { acc, curr -> acc + curr.distance },
            workouts.fold ( 0) { acc, curr -> acc + curr.calories },
        )
    }
}
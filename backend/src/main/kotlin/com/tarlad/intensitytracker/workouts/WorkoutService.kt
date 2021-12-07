package com.tarlad.intensitytracker.workouts

import org.springframework.stereotype.Service

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {

    private val workoutCSVParser = WorkoutCSVParser()

    fun bulkInputWorkoutsFromCSV(csv: String): MutableList<Workout> {
        val workouts = workoutCSVParser.parseCsv(csv);

        return workoutRepository.insert(workouts);
    }
}
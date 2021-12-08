package com.tarlad.intensitytracker.workouts

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface WorkoutRepository : MongoRepository<Workout, String> {
    fun findOneById(id: String): Workout;
    fun findWorkoutByDateBetween(from: LocalDateTime, to: LocalDateTime): List<Workout>;
}
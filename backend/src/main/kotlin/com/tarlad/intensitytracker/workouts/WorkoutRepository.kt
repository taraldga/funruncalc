package com.tarlad.intensitytracker.workouts

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface WorkoutRepository : MongoRepository<Workout, String> {
    fun findOneById(id: String): Workout;
}
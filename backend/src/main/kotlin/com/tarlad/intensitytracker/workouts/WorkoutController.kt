package com.tarlad.intensitytracker.workouts

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader

@RestController
@RequestMapping("/workout")
class WorkoutController(
    private val workoutRepository: WorkoutRepository,
    private val workoutService: WorkoutService
) {
    @GetMapping
    fun getAllWorkouts(): ResponseEntity<List<Workout>> {
        val workouts = workoutRepository.findAll();
        return ResponseEntity.ok(workouts)
    }

    @GetMapping("/{id}")
    fun getWorkoutById(@PathVariable("id") id: String): ResponseEntity<Workout> {
        val workout = workoutRepository.findOneById(id);
        return ResponseEntity.ok(workout);
    }

    @PostMapping
    fun createWorkout(@RequestBody workoutRequest: WorkoutDTO) : ResponseEntity<Workout>{

        val workout = workoutRepository.save(Workout(
            activityType = workoutRequest.activityType,
            date = workoutRequest.date,
            title = workoutRequest.title,
            distance = workoutRequest.distance,
            calories = workoutRequest.calories,
            time = workoutRequest.time,
            avgHr = workoutRequest.avgHr,
            maxHr = workoutRequest.maxHr,
            aerobicTE = workoutRequest.aerobicTE,
            avgRunCadence = workoutRequest.avgRunCadence,
            maxRunCadence = workoutRequest.maxRunCadence,
            avgPace = workoutRequest.avgPace,
            bestPace = workoutRequest.bestPace,
            totalAscent = workoutRequest.totalAscent,
            totalDescent = workoutRequest.totalDescent,
            avgStrideLength = workoutRequest.avgStrideLength,
            avgVerticalRatio = workoutRequest.avgVerticalRatio,
            avgVerticalOscillation = workoutRequest.avgVerticalOscillation,
            avgGroundContactTime = workoutRequest.avgGroundContactTime,
            avgGCTBalance = workoutRequest.avgGCTBalance,
            minTemp = workoutRequest.minTemp,
            timeZ1 = workoutRequest.timeZ1,
            timeZ2 = workoutRequest.timeZ2,
            timeZ3 = workoutRequest.timeZ3,
            timeZ4 = workoutRequest.timeZ4,
            timeZ5 = workoutRequest.timeZ5,

        ))
        return ResponseEntity(workout, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    fun updateWorkout(@RequestBody workoutRequest: WorkoutDTO, @PathVariable id: String): ResponseEntity<Workout>{

        val workout = workoutRepository.findOneById(id);
        val updatedWorkout = workoutRepository.save(Workout(
            id = workout.id,
            activityType = workoutRequest.activityType,
            date = workoutRequest.date,
            title = workoutRequest.title,
            distance = workoutRequest.distance,
            calories = workoutRequest.calories,
            time = workoutRequest.time,
            avgHr = workoutRequest.avgHr,
            maxHr = workoutRequest.maxHr,
            aerobicTE = workoutRequest.aerobicTE,
            avgRunCadence = workoutRequest.avgRunCadence,
            maxRunCadence = workoutRequest.maxRunCadence,
            avgPace = workoutRequest.avgPace,
            bestPace = workoutRequest.bestPace,
            totalAscent = workoutRequest.totalAscent,
            totalDescent = workoutRequest.totalDescent,
            avgStrideLength = workoutRequest.avgStrideLength,
            avgVerticalRatio = workoutRequest.avgVerticalRatio,
            avgVerticalOscillation = workoutRequest.avgVerticalOscillation,
            avgGroundContactTime = workoutRequest.avgGroundContactTime,
            avgGCTBalance = workoutRequest.avgGCTBalance,
            minTemp = workoutRequest.minTemp,
            timeZ1 = workoutRequest.timeZ1,
            timeZ2 = workoutRequest.timeZ2,
            timeZ3 = workoutRequest.timeZ3,
            timeZ4 = workoutRequest.timeZ4,
            timeZ5 = workoutRequest.timeZ5,

        ));
        return ResponseEntity.ok(updatedWorkout);
    }


    @PostMapping("/csv")
    fun uploadCSVFile(@RequestParam file: MultipartFile): ResponseEntity<List<Workout>> {
        val content = file.inputStream.bufferedReader().use(BufferedReader::readText);

        val insertResult = workoutService.bulkInputWorkoutsFromCSV(content);

        return ResponseEntity.ok(insertResult);
    }

    @DeleteMapping("/{id}")
    fun deleteWorkout(@PathVariable("id") id: String): ResponseEntity<Void> {
        workoutRepository.deleteById(id);

        return ResponseEntity.noContent().build<Void>();
    }
}
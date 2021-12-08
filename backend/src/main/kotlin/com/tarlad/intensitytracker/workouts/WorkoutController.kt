package com.tarlad.intensitytracker.workouts

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.time.LocalDateTime

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/workout")
class WorkoutController(
    private val workoutRepository: WorkoutRepository,
    private val workoutService: WorkoutService
) {

    // Basic CRUD operations

    @GetMapping
    fun getAllWorkouts(
        @RequestParam(required = false) from: String?,
        @RequestParam(required = false) to: String?
    ): ResponseEntity<List<Workout>> {
        val safeFrom = from ?: "1970-01-01T00:00:00";
        val safeTo = to ?: "2200-01-01T00:00:00";

        // On format yyyy-MM-ddThh:mm:ss
        val fromDate = LocalDateTime.parse(safeFrom);
        val toDate = LocalDateTime.parse(safeTo);

        val workouts = workoutRepository.findWorkoutByDateBetween(fromDate, toDate);
        return ResponseEntity.ok(workouts)
    }

    @GetMapping("/{id}")
    fun getWorkoutById(@PathVariable("id") id: String): ResponseEntity<Workout> {
        val workout = workoutRepository.findOneById(id);
        return ResponseEntity.ok(workout);
    }

    @PostMapping
    fun createWorkout(@RequestBody workoutRequest: WorkoutDTO): ResponseEntity<Workout> {

        val workout = workoutRepository.save(
            Workout(
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

                )
        )
        return ResponseEntity(workout, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    fun updateWorkout(@RequestBody workoutRequest: WorkoutDTO, @PathVariable id: String): ResponseEntity<Workout> {

        val workout = workoutRepository.findOneById(id);
        val updatedWorkout = workoutRepository.save(
            Workout(
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

                )
        );
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{id}")
    fun deleteWorkout(@PathVariable("id") id: String): ResponseEntity<Void> {
        workoutRepository.deleteById(id);

        return ResponseEntity.noContent().build<Void>();
    }

    // Other operations

    @PostMapping("/csv")
    fun uploadCSVFile(@RequestParam file: MultipartFile): ResponseEntity<List<Workout>> {
        val content = file.inputStream.bufferedReader().use(BufferedReader::readText);

        val insertResult = workoutService.bulkInputWorkoutsFromCSV(content);

        return ResponseEntity.ok(insertResult);
    }

    @GetMapping("/summary")
    fun getSummary(@RequestParam(required = false) from: String?,
                         @RequestParam(required = false) to: String?) {
        val safeFrom = from ?: "1970-01-01T00:00:00";
        val safeTo = to ?: "2200-01-01T00:00:00";

        // On format yyyy-MM-ddThh:mm:ss
        val fromDate = LocalDateTime.parse(safeFrom);
        val toDate = LocalDateTime.parse(safeTo);

        workoutService.getSummary(fromDate, toDate);



    }
}
package com.tarlad.intensitytracker

import com.tarlad.intensitytracker.workouts.WorkoutService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class WorkoutServiceTest{

    @Autowired
    lateinit var workoutService: WorkoutService;

    @Test
    fun createSummaryTest() {
        val from = "2021-12-01T00:00:00";
        val to = "2021-12-12T00:00:00";

        // On format yyyy-MM-ddThh:mm:ss
        val fromDate = LocalDateTime.parse(from);
        val toDate = LocalDateTime.parse(to);

        val summary = workoutService.getSummary(fromDate, toDate)

        println(summary.timeEasy)
        println(summary.workoutCount)
        println(summary.timeHard)
        println(summary.totalAscent)
        println(summary.totalCalories)
        println(summary.totalTime)


    }
}
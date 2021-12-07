package com.tarlad.intensitytracker

import com.tarlad.intensitytracker.workouts.WorkoutCSVParser
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.BufferedReader
import java.io.File

@SpringBootTest
class WorkoutCSVParserTest {

    val workoutCSVParser = WorkoutCSVParser()

    @Test
    fun csvParserReturnsAllWorkouts() {
        val csvFile = File("src/test/kotlin/com/tarlad/intensitytracker/files/Activities.csv")
        assert(csvFile.isFile)
        val content = csvFile.bufferedReader().use(BufferedReader::readText)
        val workouts = workoutCSVParser.parseCsv(content);

        assert(workouts.size == 40);
    }

    @Test
    fun csvParserReturnsValidWorkouts() {
        val csvFile = File("src/test/kotlin/com/tarlad/intensitytracker/files/Activities.csv")
        assert(csvFile.isFile)
        val content = csvFile.bufferedReader().use(BufferedReader::readText)
        val workouts = workoutCSVParser.parseCsv(content);

        val sampleWorkout = workouts[0];

        assert(sampleWorkout.title == "Bærum Running")
    }
}
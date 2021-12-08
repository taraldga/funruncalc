package com.tarlad.intensitytracker

import com.tarlad.intensitytracker.workouts.WorkoutCSVParser
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.BufferedReader
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @Test
    fun testLocalDateTime() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        val dateTime = LocalDateTime.parse("2021-12-04 15:37:41", formatter)
        val jsDateTime = LocalDateTime.parse("2021-12-04T14:37:41")
        println(dateTime.toLocalTime())
        println(jsDateTime.toLocalTime())
    }

}
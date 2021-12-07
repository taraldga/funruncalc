package com.tarlad.intensitytracker.workouts
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WorkoutCSVParser {

    /**
     * Returns duration in seconds from a durationstring from Garmin
     *
     * @param durationString duration as string on the format hh:mm:ss
     */
    private fun getMinutesFromDurationString(durationString: String): Int {
        val parts = durationString.split(":");

        if(parts.size < 3) {
            return 0;
        }

        val hours = parts[0].toInt();
        val minutes = parts[1].toInt();
        val seconds = parts[2].toInt();

        return (hours * 60 * 60) * (minutes * 60) + seconds;
    }

    /**
     * Returns pace per km in seconds from a pace string from Garmin
     *
     * @param paceString
     */
    private fun getPaceInSecondsFromPaceString(paceString: String): Int {
        val parts = paceString.split(":");

        if(parts.size < 2) {
            return 0;
        }

        val minutes = parts[0].toInt();
        val seconds = parts[1].toInt();

        return (minutes * 60) + seconds;
    }


    fun ensureNotGarminNull(value: String): String {
        if(value.equals("--")) {
            return "0"
        } else {
            return value;
        }
    }


    fun parseCsv(csvFile: String): List<Workout> {
        val parser = CSVParser.parse(csvFile, CSVFormat.DEFAULT.withHeader());
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        val workouts = parser.map {
            Workout(
                activityType = it.get("Activity Type"),
                date = LocalDateTime.parse(ensureNotGarminNull(it.get("Date")), formatter),
                title = ensureNotGarminNull(it.get("Title")),
                distance = ensureNotGarminNull(it.get("Distance")).toDouble(),
                calories = ensureNotGarminNull(it.get("Calories")).replace(",","").toInt(),
                time = getMinutesFromDurationString(ensureNotGarminNull(it.get("Time"))),
                avgHr = ensureNotGarminNull(it.get("Avg HR")).toInt(),
                maxHr = ensureNotGarminNull(it.get("Max HR")).toInt(),
                aerobicTE = ensureNotGarminNull(it.get("Aerobic TE")).toDouble(),
                avgRunCadence = ensureNotGarminNull(it.get("Avg Run Cadence")).toInt(),
                maxRunCadence = ensureNotGarminNull(it.get("Max Run Cadence")).toInt(),
                avgPace = getPaceInSecondsFromPaceString(ensureNotGarminNull(it.get("Avg Pace"))),
                bestPace = getPaceInSecondsFromPaceString(ensureNotGarminNull(it.get("Best Pace"))),
                totalAscent = ensureNotGarminNull(it.get("Total Ascent")).toInt(),
                totalDescent = ensureNotGarminNull(it.get("Total Descent")).toInt(),
                avgStrideLength = ensureNotGarminNull(it.get("Avg Stride Length")).toDouble(),
                avgVerticalRatio = ensureNotGarminNull(it.get("Avg Vertical Ratio")).toDouble(),
                avgVerticalOscillation = ensureNotGarminNull(it.get("Avg Vertical Oscillation")).toDouble(),
                avgGroundContactTime = ensureNotGarminNull(it.get("Avg Ground Contact Time")).toInt(),
                avgGCTBalance = ensureNotGarminNull(it.get("Avg GCT Balance")),
                minTemp = ensureNotGarminNull(it.get("Min Temp")).toDouble(),
                timeZ1 = 0,
                timeZ2 = 0,
                timeZ3 = 0,
                timeZ4 = 0,
                timeZ5 = 0,
            );
        }

        return workouts;
    }
}
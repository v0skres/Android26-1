package parser
import model.*;
import java.io.File

object CsvParser {
    fun parseLine(line: String): Player {
        val values = line.split(';');
        return Player(
            name = values[0],
            team = Team(values[1]),
            city = values[2],
            position = Position.valueOf(values[3]),
            nationality = values[4],
            agency = values[5],
            transferCost = values[6].toInt(),
            participations = values[7].toInt(),
            goals = values[8].toInt(),
            assists = values[9].toInt(),
            yellowCards = values[10].toInt(),
            redCards = values[11].toInt()
        );
    }

    fun parseFile(filename: String): List<Player> {
        return File(filename)
            .readLines()
            .drop(1)
            .map { parseLine(it) }
    }
}
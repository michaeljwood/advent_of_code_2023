fun main() {
    val day = "02"
    val checkValue = 8
    val checkValue2 = 2286

    data class Round(
            val red: Int,
            val green: Int,
            val blue: Int,
    )

    data class Game(
            val id: Int,
            val rounds: List<Round>
    )

    fun parseGame(line: String): Game {
        val id = line.substringAfter("Game ").substringBefore(":").toInt()
        val rounds = line.substringAfter(":").split(";").map {
            var round = Round(0, 0, 0)
            it.split(",").map(String::trim).forEach { group ->
                val number = group.substringBefore(" ").toInt()
                when {
                    group.endsWith("red") -> round = round.copy(red = number)
                    group.endsWith("blue") -> round = round.copy(blue = number)
                    group.endsWith("green") -> round = round.copy(green = number)
                }
            }
            round
        }

        return Game(id, rounds)
    }

    fun part1(input: List<String>): Int {
        return input.filter(String::isNotBlank)
                .map { parseGame(it) }
                .filter { game -> game.rounds.all { round -> round.red <= 12 && round.green <= 13 && round.blue <= 14 } }
                .sumOf { game -> game.id }
    }

    fun part2(input: List<String>): Int {
        return input.filter(String::isNotBlank)
                .map { parseGame(it).rounds }
                .map { rounds -> Triple(rounds.maxOf { it.red }, rounds.maxOf { it.green }, rounds.maxOf { it.blue }) }
                .sumOf { it.first * it.second * it.third }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    validateTestCase(part1(testInput), checkValue)

    val input = readInput("Day${day}")
    part1(input).println()

    val testInput2 = readInput("Day${day}_test")
    validateTestCase(part2(testInput2), checkValue2)

    part2(input).println()
}

fun main() {
    val day = "01"
    val checkValue = 142
    val checkValue2 = 281

    val digits = mapOf(
            "zero" to '0',
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9',
    ).toList()

    fun part1(input: List<String>): Int {
        return input.filter(String::isNotBlank)
                .sumOf { line ->
                    line.filter(Char::isDigit).let {
                        "${it.first()}${it.last()}"
                    }.toInt()
                }
    }

    fun part2(input: List<String>): Int {
        return input.filter(String::isNotBlank)
                .map { line ->
                    line.mapIndexed { index, c ->
                        line.substring(index).let {
                            digits.firstOrNull { digit -> it.startsWith(digit.first) }?.second
                        } ?: c
                    }
                }.sumOf { line ->
                    line.filter(Char::isDigit).let {
                        "${it.first()}${it.last()}"
                    }.toInt()
                }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    validateTestCase(part1(testInput), checkValue)

    val input = readInput("Day${day}")
    part1(input).println()

    val testInput2 = readInput("Day${day}_test2")
    validateTestCase(part2(testInput2), checkValue2)

    part2(input).println()
}

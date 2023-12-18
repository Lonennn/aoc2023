fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { convert(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { convert(parse(it)) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun convert(str: String): Int {
    val first = str.first { it.isDigit() }.digitToInt()
    val last = str.last { it.isDigit() }.digitToInt()
    return 10 * first + last
}

val numberStrings = listOf(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
)

fun parse(str: String): String {
    val parsed = ArrayList<Char>()
    for (i in str.indices) {
        if (!str[i].isLetter()) {
            parsed.add(str[i])
            continue
        }
        var j = 0
        while (j < numberStrings.size) {
            val number = numberStrings[j]
            if (str.regionMatches(i, number, 0, number.length)) {
                parsed.add((j + 1 + '0'.code).toChar())
                break
            }
            ++j
        }
        if (j == numberStrings.size) {
            parsed.add(str[i])
        }
    }
    return parsed.toCharArray().contentToString()
}
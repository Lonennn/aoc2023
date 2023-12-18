import kotlin.math.max

fun main() {
    val maxCubes = mapOf(Pair("red", 12), Pair("green", 13), Pair("blue", 14))

    fun part1(input: List<String>): Int = input.sumOf { game ->
        val gsp = game.split(":")
        var id = gsp[0].split(" ")[1].toInt()
        for (put in gsp[1].split(";")) {
            if (id == 0) break
            for (cube in put.split(", ")) {
                val (numString, color) = cube.trim().split(" ")
                if (numString.toInt() > maxCubes[color]!!) {
                    id = 0
                    break
                }
            }
        }
        id
    }


    fun part2(input: List<String>): Int = input.sumOf { game ->
        val mins = mutableMapOf(Pair("red", -1), Pair("green", -1), Pair("blue", -1))
        for (put in game.split(":")[1].trim().split("; ")) {
            for (cube in put.split(", ")) {
                val (numStr, color) = cube.split(" ")
                mins[color] = max(mins[color]!!, numStr.toInt())
            }
        }
        mins["red"]!! * mins["green"]!! * mins["blue"]!!
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

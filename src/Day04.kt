import kotlin.math.min

fun main() {
    fun splitWinningAndHave(str: String): Pair<Set<Int>, List<Int>> {
        val (winningStr, haveStr) = str.split(": ")[1].split(" | ")
        return Pair(
            HashSet<Int>().apply { winningStr.split(" ").filter { it != "" }.forEach{ add(it.toInt()) } },
            haveStr.split(" ").filter { it != "" }.map { it.toInt() }
        )
    }

    fun part1(input: List<String>): Long = input.sumOf {
        val (winning, having) = splitWinningAndHave(it)
        val matchCnt = having.count { winning.contains(it) }
        if (matchCnt == 0) 0L else (1L shl (matchCnt-1))
    }

    fun part2(input: List<String>): Long {
        val countCards = LongArray(input.size) { 1L }
        for (i in countCards.indices) {
            val (winning, having) = splitWinningAndHave(input[i])
            val matchCnt = having.count { winning.contains(it) }
            for (j in i+1 until min(input.size, i+matchCnt+1)) {
                countCards[j] += countCards[i]
            }
        }
        return countCards.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13L)
    check(part2(testInput) == 30L)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
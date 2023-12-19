fun main() {
    fun Char.isNotSymbol(): Boolean {
        return this.isDigit() || this == '.'
    }

    fun CharArray.partToInt(indices: IntRange): Int {
        var value = 0
        for (i in indices) {
            value = 10 * value + this[i].code - '0'.code
        }
        return value
    }

    fun CharArray.set(indices: IntRange, value: Char) {
        for (i in indices) {
            this[i] = value
        }
    }

    fun part1(input: List<String>): Int {
        val engine = input.map { it.toCharArray() }
        var sum = 0
        for (i in engine.indices) {
            val row = engine[i]
            for (j in row.indices) {
                if (row[j].isNotSymbol()) continue
                row[j] = '.'
                for (x in i-1..i+1) {
                    if (x < 0 || x >= engine.size) continue
                    for (y in j-1..j+1) {
                        if (y < 0 || y > row.size || !engine[x][y].isDigit()) continue
                        var (left, right) = Pair(y, y)
                        while (left > 0 && engine[x][left-1].isDigit()) --left
                        while (right != engine[x].size-1 && engine[x][right+1].isDigit()) ++right
                        sum += engine[x].partToInt(left..right)
                        engine[x].set(left..right, '.')
                    }
                }
            }
        }
        return sum
    }

    fun expandInt(str: String, y: Int): Int {
        var (left, right) = Pair(y, y)
        while (left > 0 && str[left-1].isDigit()) --left
        while (right+1 < str.length && str[right+1].isDigit()) ++right
        return str.substring(left, right+1).toInt()
    }

    fun addRowNumber(row: String, center: Int, nums: ArrayList<Int>) {
        if (row[center].isDigit()) {
            nums.add(expandInt(row, center))
            return
        }
        if (center > 0 && row[center-1].isDigit()) nums.add(expandInt(row, center-1))
        if (center+1 < row.length && row[center+1].isDigit()) nums.add(expandInt(row, center+1))
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] != '*') continue
                val nums = ArrayList<Int>()
                addRowNumber(input[i], j, nums)
                if (i > 0) addRowNumber(input[i-1], j, nums)
                if (i+1 < input.size) addRowNumber(input[i+1], j, nums)
                if (nums.size == 2) sum += nums[0] * nums[1]
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
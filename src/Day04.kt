@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

fun main() {
    fun part1(input: List<String>): String {
        var count = 0
        for (s in input) {
            val split = s.split(",", "-").map { it.toInt() }
            if (split[0] >= split[2] && split[1] <= split[3] ||
                split[2] >= split[0] && split[3] <= split[1]) {
                count++
            }
        }
        return "$count"
    }

    fun part2(input: List<String>): String {
        var count = 0
        for (s in input) {
            val split = s.split(",", "-").map { it.toInt() }
            if (split[0] <= split[2] && split[2] <= split[1] ||
                split[0] <= split[3] && split[3] <= split[1] ||
                split[2] <= split[0] && split[0] <= split[3] ||
                split[2] <= split[1] && split[1] <= split[3]
            ) {
                count++
            }
        }
        return "$count"
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("2", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("4", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

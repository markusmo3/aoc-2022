@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

fun main() {
    fun firstAfterWindow(s: String, windowSize: Int): Int {
        val first = s.toCharArray().mapIndexed { index, c -> Pair(index, c) }
            .windowed(windowSize, partialWindows = true).first {
                it.map { it.second }.distinct().size == windowSize
            }
        println(first.toString())
        return first[0].first + windowSize
    }

    fun part1(input: List<String>): String {
        return firstAfterWindow(input[0], 4).toString()
    }

    fun part2(input: List<String>): String {
        return firstAfterWindow(input[0], 14).toString()
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("11", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("26", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

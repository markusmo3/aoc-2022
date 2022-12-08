@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

import kotlin.math.max

fun main() {

    fun isTreeVisibleFromDir(
        field: Array<Array<Char>>,
        y: Int,
        x: Int,
        direction: Int,
        curHeight: Char,
        maxTravel: Int
    ): Boolean {
        val dx = if (direction == 2) 1 else if (direction == 4) -1 else 0
        val dy = if (direction == 1) -1 else if (direction == 3) 1 else 0
        for (i in 1..maxTravel) {
            val newX = x + dx*i
            val newY = y + dy*i
            if (newX < 0 || newX >= field[0].size || newY < 0 || newY >= field.size) {
                return true
            } else if (field[newY][newX] >= curHeight) {
                return false
            }
        }
        return true
    }

    fun isTreeVisible(field: Array<Array<Char>>, y: Int, x: Int): Boolean {
        val curHeight = field[y][x]
        val maxTravel = max(field.size, field[0].size)
        return isTreeVisibleFromDir(field, y, x, 1, curHeight, maxTravel)
                || isTreeVisibleFromDir(field, y, x, 2, curHeight, maxTravel)
                || isTreeVisibleFromDir(field, y, x, 3, curHeight, maxTravel)
                || isTreeVisibleFromDir(field, y, x, 4, curHeight, maxTravel)
    }

    fun part1(input: List<String>): String {
        val field: Array<Array<Char>> = Array(input.size) { Array(input[0].length) {'#'} }
        for ((index, s) in input.withIndex()) {
            field[index] = s.toCharArray().toTypedArray()
        }
        input.withIndex()
        var visibleTreeCount = 0
        for ((y, s) in field.withIndex()) {
            for ((x, _) in s.withIndex()) {
                if (isTreeVisible(field, y, x)) {
                    visibleTreeCount++
                }
            }
        }
        return "$visibleTreeCount"
    }


    fun getTreeAmountFromDir(
        field: Array<Array<Char>>,
        y: Int,
        x: Int,
        direction: Int,
        curHeight: Char,
        maxTravel: Int
    ): Int {
        val dx = if (direction == 2) 1 else if (direction == 4) -1 else 0
        val dy = if (direction == 1) -1 else if (direction == 3) 1 else 0
        var count = 0
        for (i in 1..maxTravel) {
            val newX = x + dx*i
            val newY = y + dy*i

            if (newX < 0 || newX >= field[0].size || newY < 0 || newY >= field.size) {
                return count
            } else if (curHeight > field[newY][newX]) {
                count++
            } else {
                return count + 1
            }
        }
        return count
    }

    fun getTreeScore(field: Array<Array<Char>>, y: Int, x: Int): Int {
        val curHeight = field[y][x]
        val maxTravel = max(field.size, field[0].size)
        return getTreeAmountFromDir(field, y, x, 1, curHeight, maxTravel) *
                getTreeAmountFromDir(field, y, x, 2, curHeight, maxTravel) *
                getTreeAmountFromDir(field, y, x, 3, curHeight, maxTravel) *
                getTreeAmountFromDir(field, y, x, 4, curHeight, maxTravel)
    }

    fun part2(input: List<String>): String {
        val field: Array<Array<Char>> = Array(input.size) { Array(input[0].length) {'#'} }
        for ((index, s) in input.withIndex()) {
            field[index] = s.toCharArray().toTypedArray()
        }
        input.withIndex()
        var bestTreeScore = 0
        for ((y, s) in field.withIndex()) {
            for ((x, _) in s.withIndex()) {
                val score = getTreeScore(field, y, x)
                if (bestTreeScore < score) {
                    bestTreeScore = score
                }
            }
        }
        return "$bestTreeScore"
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("21", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("8", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

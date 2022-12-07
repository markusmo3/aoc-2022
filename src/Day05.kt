@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

import java.util.*

fun main() {
    fun parse(towerStrings: List<String>): Array<Deque<Char>> {
        val towerAmount = towerStrings[towerStrings.size - 1].trim().split("   ").size
        val towerList = Array<Deque<Char>>(towerAmount) { LinkedList() }

        println("towerAmount=$towerAmount")
        for (lineIndex in towerStrings.size-2  downTo 0) {
            val line = towerStrings[lineIndex].toCharArray()
            for (towerIndex in 0 until towerAmount) {
                val targetIndex = 1 + (towerIndex) * 4
                if (targetIndex < line.size && line[targetIndex] != ' ') {
                    val towerQueue = towerList[towerIndex]
                    towerQueue.offer(line[targetIndex])
                    towerList[towerIndex] = towerQueue
                }
            }
        }
        return towerList
    }

    fun crateMover(input: List<String>, multipleAtOnce: Boolean): String {
        val towers = parse(input.takeWhile { it != "" })
        for (s in input.dropWhile { it != "" }) {
            if (s == "") continue
            val split = s.split(' ')
            val amount = split[1].toInt()
            val moveFrom = split[3].toInt()
            val moveTo = split[5].toInt()

            if (multipleAtOnce) {
                val tmpDeque = LinkedList<Char>()
                for (i in 1..amount) {
                    tmpDeque.offer(towers[moveFrom - 1].pollLast())
                }
                for (i in 1..amount) {
                    towers[moveTo - 1].offer(tmpDeque.pollLast())
                }
            } else {
                for (i in 1..amount) {
                    towers[moveTo - 1].offer(towers[moveFrom - 1].pollLast())
                }
            }
        }
        towers.forEachIndexed { index, q -> println("$index: $q") }
        return towers.map { it.pollLast() }.joinToString(separator = "") { it.toString() }
    }

    fun part1(input: List<String>): String {
        return crateMover(input, false)
    }

    fun part2(input: List<String>): String {
        return crateMover(input, true)
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("CMZ", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("MCD", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

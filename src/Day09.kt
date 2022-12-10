@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

import kotlin.math.abs
import kotlin.math.min

data class Coord(var x: Int, var y: Int) {
    operator fun plusAssign(delta: Coord) {
        x += delta.x
        y += delta.y
    }

    operator fun minus(coord: Coord): Coord {
        return Coord(x - coord.x, y - coord.y)
    }

    fun getStepsRequired(): Int {
        val ax = abs(x)
        val ay = abs(y)
        val diagonal = min(ax, ay)
        return ax + ay - diagonal
    }

    fun unit(): Coord {
        return Coord(
            if (x == 0) 0 else x / abs(x),
            if (y == 0) 0 else y / abs(y)
        )
    }
}

fun main() {
    fun printSetOfCoords(set: Set<Coord>) {
        var minx = 0
        var miny = 0
        var maxx = 0
        var maxy = 0
        for (coord in set) {
            if (coord.x < minx) minx = coord.x
            if (coord.y < miny) miny = coord.y
            if (coord.x > maxx) maxx = coord.x
            if (coord.y > maxy) maxy = coord.y
        }
        val width = abs(minx) + abs(maxx)
        val height = abs(miny) + abs(maxy)
        for (y in height downTo 0) {
            for (x in 0 .. width) {
                if (minx + x == 0 && miny + y == 0) {
                    print('S')
                } else if (set.contains(Coord(minx + x, miny + y))) {
                    print('#')
                } else {
                    print('.')
                }
            }
            println()
        }
    }

    fun walkKnotList(
        input: List<String>,
        knotList: List<Coord>
    ): MutableSet<Coord> {
        val setOfTailPositions = mutableSetOf(knotList.last().copy())
        for (s in input) {
            val delta = Coord(
                if (s[0] == 'L') -1 else if (s[0] == 'R') 1 else 0,
                if (s[0] == 'D') -1 else if (s[0] == 'U') 1 else 0
            )
            val amount = s.substring(2).toInt()
            for (i in 1..amount) {
                for ((index, knot) in knotList.withIndex()) {
                    if (index == 0) {
                        knot += delta
                    } else {
                        val vector = knotList[index - 1] - knot
                        if (vector.getStepsRequired() <= 1) {
                            continue
                        }
                        knot += vector.unit()
                        if (index == knotList.size - 1) {
                            setOfTailPositions.add(knot.copy())
                        }
                    }
                }
            }
        }
        return setOfTailPositions
    }

    fun part1(input: List<String>): String {
        val knotList = listOf(Coord(0, 0), Coord(0, 0))
        val setOfTailPositions = walkKnotList(input, knotList)
        printSetOfCoords(setOfTailPositions)
        return "${setOfTailPositions.size}"
    }

    fun part2(input: List<String>): String {
        val knotList = listOf(
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0),
            Coord(0, 0)
        )
        val setOfTailPositions = walkKnotList(input, knotList)
//        printSetOfCoords(setOfTailPositions)
        return "${setOfTailPositions.size}"
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("13", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    val part2TestInput = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
        """.trimIndent().trim().lines()
    checkEquals("36", part2(part2TestInput))
    println("=> Part2: " + part2(readInput()))
}

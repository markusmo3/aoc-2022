@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

fun main() {
    fun part1(input: List<String>): String {
        var cycle = 1
        var x = 1
        var total = 0

        fun addToTotalMaybe() {
            if ((cycle - 20) % 40 == 0) total += cycle * x
        }

        for (s in input) {
            addToTotalMaybe()
            if (s.startsWith("noop")) {
                cycle++
            } else if (s.startsWith("addx")) {
                val dx = s.substring(5).toInt()
                cycle++
                addToTotalMaybe()
                cycle++
                x += dx
            }
        }
        return "$total"
    }

    fun part2(input: List<String>): String {
        var cycle = 1
        var x = 1

        fun drawPixel() {
            val screenIndex = (cycle-1) % 40
            if (screenIndex == 0 && cycle > 1) {
                println()
            }
            if (screenIndex == x || screenIndex == x-1 || screenIndex == x+1) {
                print('#')
            } else {
                print('.')
            }

        }

        for (s in input) {
            if (s.startsWith("noop")) {
                drawPixel()
                cycle++
            } else if (s.startsWith("addx")) {
                val dx = s.substring(5).toInt()
                drawPixel()
                cycle++

                drawPixel()
                x += dx
                cycle++
            }
        }
        println()
        return ""
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("13140", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

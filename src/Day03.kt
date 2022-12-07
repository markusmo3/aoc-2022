@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

fun main() {
    fun charToNumber(c: Char): Int {
        var character = c - 'A' + 1
        if (character <= 26) {
            character += 26
        } else {
            character -= 32
        }
        return character
    }

    fun part1(input: List<String>): String {
        var total = 0
        for (s in input) {
            val first = s.substring(0, s.length / 2)
            val second = s.substring(s.length / 2, s.length)
            val intersect = first.toCharArray().toList().intersect(second.toCharArray().toList())
            total += charToNumber(intersect.first())
        }
        return "$total"
    }

    fun part2(input: List<String>): String {
        var total = 0
        for (i in 0 until input.size step 3) {
            val intersect = input[i].toCharArray().toList()
                .intersect(input[i+1].toCharArray().toList())
                .intersect(input[i+2].toCharArray().toList())
            println(intersect)
            total += charToNumber(intersect.first())
        }
        return "$total"
    }


    checkEquals(27, charToNumber('A'))
    checkEquals(1, charToNumber('a'))
    println("================ ${getCallerClass()} =================")
    checkEquals("157", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("70", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

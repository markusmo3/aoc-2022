import kotlin.system.exitProcess

fun main() {
    fun rps(s: String, part2Active: Boolean = false): Int {
        if (s == "") return 0
        var pointsThisRound = 0
        val enemy = s[0].code - 65
        var me = s[2].code - 88

        if (part2Active) {
            if (me == 0) {
                me = (enemy + 2) % 3
            } else if (me == 1) {
                me = (enemy) % 3
            } else if (me == 2) {
                me = (enemy + 1) % 3
            }
        }

        pointsThisRound += me + 1
        if (enemy == me) {
            pointsThisRound += 3 // draw
        } else if ((enemy+1)%3 == me) {
            pointsThisRound += 6 // we win
        }
        return pointsThisRound
    }

    fun part1(input: List<String>): String {
        var totalPoints = 0
        for (s in input) {
            totalPoints += rps(s)
        }
        return "$totalPoints"
    }

    fun part2(input: List<String>): String {
        var totalPoints = 0
        for (s in input) {
            totalPoints += rps(s, part2Active = true)
        }
        return "$totalPoints"
    }

    println("================ ${getCallerClass()} =================")
    checkEquals(4, rps("A X"))
    checkEquals(5, rps("B Y"))
    checkEquals(6, rps("C Z"))
    checkEquals(8, rps("A Y"))
    checkEquals(9, rps("B Z"))
    checkEquals(7, rps("C X"))
    checkEquals(3, rps("A Z"))
    checkEquals(1, rps("B X"))
    checkEquals(2, rps("C Y"))
    println("========================================")
    val input = readInput()
    checkEquals("15", part1(readTestInput()))
    println("Part1: " + part1(input))
    println("========================================")
    checkEquals("12", part2(readTestInput()))
    println("Part2: " + part2(input))
}

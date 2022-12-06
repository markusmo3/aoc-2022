fun main() {
    fun part1(input: List<String>): String {
        var elfNo = 1
        var curCal = 0
        var maxElf = 0
        var maxCal = 0
        for (s in input) {
            if (s == "") {
                if (curCal > maxCal) {
                    maxCal = curCal
                    maxElf = elfNo
                }
                elfNo++
                curCal = 0
            } else {
                curCal += s.toInt()
            }
        }
        return "$maxElf: $maxCal"
    }

    fun part2(input: List<String>): String {
        // brute force
        val elves = mutableListOf<Int>()
        var curCal = 0
        for (s in input) {
            if (s == "") {
                elves.add(curCal)
                curCal = 0
            } else {
                curCal += s.toInt()
            }
        }
        elves.sortDescending()
        return elves.take(3).sum().toString()
    }

    val expected = "4: 24000"
    check(part1(readTestInput()) == expected)

    val input = readInput()
    println(part1(input))
    println(part2(input))
}

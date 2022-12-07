@file:Suppress("UNUSED_PARAMETER", "DuplicatedCode", "KotlinRedundantDiagnosticSuppress")

fun main() {
    fun propagateSize(
        curDir: String,
        dirs: MutableMap<String, Long>,
        curSize: Long
    ) {
        if (curDir == "/") {
            dirs.merge("/", curSize) { i, j -> i + j }
            return
        }

        var propagateDir = curDir
        while (propagateDir != "") {
            dirs.merge(propagateDir, curSize) { i, j -> i + j }
            propagateDir = propagateDir.substring(0, propagateDir.lastIndexOf('/'))
            if (propagateDir == "") {
                // reached the root
                dirs.merge("/", curSize) { i, j -> i + j }
                break
            }
        }
    }

    fun parseDirSizes(input: List<String>): MutableMap<String, Long> {
        val dirs = mutableMapOf<String, Long>()
        var curDir = ""
        var curSize: Long = 0
        for (s in input) {
            if (s.startsWith("$") && curSize > 0) {
                propagateSize(curDir, dirs, curSize)
                curSize = 0
            }

            if (s.startsWith("$ cd")) {
                val newDir = s.substring(5)
                if (newDir == "/") {
                    curDir = "/"
                } else if (newDir == "..") {
                    curDir = curDir.substring(0, curDir.lastIndexOf('/'))
                } else {
                    if (curDir == "/") {
                        curDir += newDir
                    } else {
                        curDir += "/$newDir"
                    }
                }
            } else if (s.startsWith("$ ls")) {
                // NOOP
            } else if (s.startsWith("dir")) {
                // NOOP
            } else {
                val fileSize = s.split(' ')[0].toInt()
                curSize += fileSize
            }
        }
        if (curSize > 0) {
            propagateSize(curDir, dirs, curSize)
        }
        return dirs
    }

    fun part1(input: List<String>): String {
        val dirs = parseDirSizes(input)

        dirs.toSortedMap().forEach { t, u -> println("$t=$u") }
        return dirs.filterValues { it <= 100000 }.values.sum().toString()
    }

    fun part2(input: List<String>): String {
        // total disk 70000000
        // need at least 30000000

        val dirs = parseDirSizes(input)
        val currentUnusedSize = 70_000_000 - dirs["/"]!!
        val sizeNeededForDeletion = 30_000_000 - currentUnusedSize
        println("rootSize=${dirs["/"]}")
        println("currentUnusedSize=$currentUnusedSize")
        println("sizeNeededForDeletion=$sizeNeededForDeletion")
//        dirs.toSortedMap().forEach { t, u -> println("$t=$u") }
        val sortedByValue =
            dirs.filterValues { it > sizeNeededForDeletion }.entries.sortedBy { it.value }
        sortedByValue.forEach{ e -> println(e) }
        return dirs.filterValues { it > sizeNeededForDeletion }.minBy { it.value }.toString()
    }


    println("================ ${getCallerClass()} =================")
    checkEquals("95437", part1(readTestInput()))
    println("=> Part1: " + part1(readInput()))
    println("========================================")
    checkEquals("/d=24933642", part2(readTestInput()))
    println("=> Part2: " + part2(readInput()))
}

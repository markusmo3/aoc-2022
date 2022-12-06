import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readTestInput(): List<String> {
    return File("src", "${getCallerClass(3)}_test.txt").readLines()
}

fun readInput(): List<String> {
    return File("src", "${getCallerClass(3)}_input.txt").readLines()
}

fun getCallerClass(index: Int = 3): String {
    var callerClass = Thread.currentThread().getStackTrace()[index].getClassName()
    if (callerClass.contains("_")) {
        callerClass = callerClass.substring(0, callerClass.indexOf('_'))
    }
    if (callerClass.endsWith("Kt")) {
        callerClass = callerClass.substring(0, callerClass.length - 2)
    }
    return callerClass
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5")
    .digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun checkEquals(expected: Any, actual: Any) {
    if (expected == actual) {
        println("SUCCESS: $expected == $actual")
    } else {
        println("FAILED:  $expected != $actual")
    }
}

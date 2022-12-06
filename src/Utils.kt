import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readTestInput(): List<String> {
    var callerClass = Thread.currentThread().getStackTrace()[2].getClassName()
    if (callerClass.contains("_")) {
        callerClass = callerClass.substring(0, callerClass.indexOf('_'))
    }
    return File("src", "${callerClass}_test.txt").readLines()
}

fun readInput(): List<String> {
    var callerClass = Thread.currentThread().getStackTrace()[2].getClassName()
    if (callerClass.contains("_")) {
        callerClass = callerClass.substring(0, callerClass.indexOf('_'))
    }
    return File("src", "${callerClass}_input.txt").readLines()
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

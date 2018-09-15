package hackerrank

import java.util.*
import kotlin.text.*


fun appendAndDelete(s: String, t: String, k: Int): String{
    val sLen = s.length
    val tLen = t.length
    val pivot = if(sLen<tLen) sLen else tLen
    println(s.compareTo(t))

    return "Yes"
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val s = scan.nextLine()

    val t = scan.nextLine()

    val k = scan.nextLine().trim().toInt()

    val result = appendAndDelete(s, t, k)

    println(result)
}
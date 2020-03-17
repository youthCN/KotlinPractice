package cn.gaocy.ktpractice.chapter03_function

import cn.gaocy.ktpractice.chapter03_function.ext.lastChar
import cn.gaocy.ktpractice.chapter03_function.ext.myJoinToString
import cn.gaocy.ktpractice.chapter03_function.utils.joinToString
import java.lang.StringBuilder

fun main(args: Array<String>) {
    val joinToString = joinToString(listOf(1, 2, 3, 4, 5))
    println("""joinToString result = ${joinToString}""")

    val lastChar = "Kotlin".lastChar()
    println("""lastChar=${lastChar}""")

    val myJoinToString = listOf<Int>(1, 2, 3, 4, 5).myJoinToString()
    println("""myJoinToString=${myJoinToString}""")

    val stringBuilder = StringBuilder("Kotlin!")
    println(stringBuilder.lastChar)
    stringBuilder.lastChar = '?'
    println(stringBuilder)
}
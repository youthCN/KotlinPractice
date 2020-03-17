package cn.gaocy.ktpractice.chapter03_function.infix

infix fun Any.to(other: Any) = Pair(this, other)

fun main(args: Array<String>) {
    val (number, name) = 1 to "one"
    val mapOf = mapOf(1 to "one", 2 to "two")
}
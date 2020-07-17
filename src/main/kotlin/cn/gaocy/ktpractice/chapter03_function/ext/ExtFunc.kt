package cn.gaocy.ktpractice.chapter03_function.ext

import java.lang.StringBuilder

/**
 * 扩展函数是静态函数,它把调用的对象作为了它的第一个参数.
 */
fun String.lastChar(): Char {
    return this.get(length - 1)//不能访问私有的和受保护的成员
}

//fun String.lastChar(): Char =this.get(length-1)

fun <T> Collection<T>.myJoinToString(sep: String = ",", prefix: String = "[", postfix: String = "]"): String {
    var result = StringBuilder(prefix);
    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(sep)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main(args:Array<String>) {
    val array = arrayOf(2,1,2);
    val list0 = arrayListOf<Int>(*array)//使用展开数据
    val list1 = listOf<Int>(1,2,3)
    val mapOf0 = mapOf<Int, String>(1 to "a", 2 to "b")

}
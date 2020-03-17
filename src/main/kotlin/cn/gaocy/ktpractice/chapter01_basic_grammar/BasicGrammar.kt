package cn.gaocy.ktpractice.chapter01_basic_grammar

import java.io.File

fun main(args: Array<String>) {
    println(sum1(1, 2))
    println(sum2(1, 2))
    printSum1(2, 4)
    testFor1()
    testFor2()
    testRange()
    testNodeNull()

}

//表达式作为函数体、返回值类型自动推断的函数：
fun sum1(a: Int, b: Int) = a + b

fun sum2(a: Int, b: Int): Int {
    return a + b
}

fun printSum1(a: Int, b: Int): Unit {
    println("a+b=" + (a + b))
}

//Unit 可以省略
fun printSum2(a: Int, b: Int) {
    println("a+b=" + (a + b))
}

fun getStringLen(obj: Any): Int? {
    if (obj is String) {
        return obj.length;
    }
    return null;
}

fun testFor1() {
    val list = listOf<String>("apple", "banana", "kiwifruit")
    for (item in list) {
        print(item + ",")
    }
}

fun testFor2() {
    val list = listOf<String>("apple", "banana", "kiwifruit")
    for (index in list.indices) {
        print(list[index] + ",")
    }
    println()
}

fun testWhen(obj: Any): String =
        when (obj) {
            1 -> "1"
            is Long -> "Long"
            else -> "unknown"
        }

fun testRange() {
    val x = 1;
    val y = 10;
    if (x in 0..y) {
        println("x is in 0--" + y)
    }

    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
    println()
}

//创建 DTOs
data class Person(val name: String, val age: Int) {

}

//方法的默认参数
fun foo(a: Int = 0, b: String = "哈喽") {

}

fun createList(): Unit {
    val list = listOf("a", "b", "c")
}

fun createMap(): Unit {
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    map.forEach({
        println(it)
    })
    println(map["a"])
}

fun testNodeNull(){
    val files = File("Test").listFiles()
    println(files?:"empty");//if null 执行一个语句
    println(files?.size);//If not null 缩写
    println(files?.size ?: "empty")//If not null and else 缩写
}

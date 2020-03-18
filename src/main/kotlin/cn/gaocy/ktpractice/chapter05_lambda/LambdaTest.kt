package cn.gaocy.ktpractice.chapter05_lambda

import cn.gaocy.ktpractice.chapter04_class.classObj.Person
import java.lang.StringBuilder

/**
 * lambda表达式是什么:
 *  1.就是一个表达式,所以是在{}内部;
 *  2.它本质上就是一个函数类型的实现.
 */

/**
 * 这里就是省略了 sum 变量的类型: (Int,Int)->Int,该类型是函数类型.
 * 本质上就是 val 变量: 类型 = 实现/实例
 */
val sum = { x: Int, y: Int ->
    println("""you input x=${x},y=${y}""")
    x + y
}

/**
 * op 是一个变量,它的类型是函数类型.当调用sum2方法的时候,传入的op,应该是一个lambda表达式
 *  相当于可以看作 lambda表达式就是对函数类型的一个实现
 */
fun sum2(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}

fun main(args: Array<String>) {

    sum2(1,2,{a,b ->a+b})

    //成员引用----------------------------------------
    val person = Person("gcy")
    val name = Person::name
    println(name(person))
    val say = Person::say
    say(person)
    //----------------------------------------
    listOf<Int>(1, 2, 3, 4, 597, 6, 5).forEach(::println)
    listOf<Int>(1, 2, 3, 4, 597, 6, 5).forEach({ num: Int ->
        print("""num=${num},""")
    })

    //all
    val listOf = listOf<Int>(1, 2, 3, 4, 597, 6, 5, 1, 2)
    val all = listOf.all { it > 2 }
    println("all =${all}")
    val any = listOf.any { it > 2 }
    println("any =${any}")

    val count = listOf.count { it > 6 }
    println("count =${count}")

    val find = listOf.find { it > 3 }
    println("find =${find}")


    val listPerson = listOf(Person("g", "female", "nanbu", 12),
            Person("g", "male", "chengdu", 12),
            Person("a", "female", "nanbu", 10),
            Person("c", "female", "nanbu", 10),
            Person("a", "female", "nanbu", 12),
            Person("l", "male", "nanbu", 22)
    )
    val groupBy = listPerson.groupBy { it.age }
    println("groupBy=" + groupBy)

    val listContainList = listOf(listOf(1,2,3),listOf(2,3,4),listOf(7,8,9))
    val flatMap = listContainList.flatMap {
        it
    }
    println("flatMap=${flatMap}")

    /*  序列          */
    val toList = listPerson.asSequence()
            .map { it.name }
            .filter { it == "a" }
            .toList()
    println("toList = ${toList}")

    val toList2 = listPerson.map { it.age }.filter { it > 10 }
    println("toList2 = ${toList2}")

    /* with */
    val result = with(StringBuilder()) {
        for (el in 'A'..'Z') {
            append(el)
        }
        append("\nend~")
    }
    println("result=${result}")

    /* apply */
    val apply = StringBuilder().apply {
        for (el in 'a'..'z') {
            append(el)
        }
        append("\nend~")
    }
    println("apply=${apply}")

}
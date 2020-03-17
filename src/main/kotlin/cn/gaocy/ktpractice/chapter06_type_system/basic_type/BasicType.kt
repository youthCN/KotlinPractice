package cn.gaocy.ktpractice.chapter06_type_system.basic_type

import java.lang.Exception
import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    test1(null)
}

// Unit类型
open class Cl<T> {
    open fun say(t: T): T {
        println("you intput t=${t}")
        return t
    }
}

class ClSon : Cl<Unit>() {
    //不需要显式的调用return
    override fun say(t: Unit) {

    }
}

/*  Nothing 类型
Nothing 类型没有任何值, 只有被当作函数返回值使用，或者被当作泛型函数返回值的类型参数使用才会有意义。
在其他所有情况下，声明 一个不能存储任何值的变量没有任何意义 。
*/
fun fail(str: String): Nothing {
    throw IllegalArgumentException(str)
}

fun test1(address: String?) {
    try {
        //这里相当于告诉编译器,后面的address都是非空的,因为为空,不能执行到后面去
        val result = address ?: fail("address is null")
        println("address.length=${result}")
    } catch (e: Exception) {

    }

}
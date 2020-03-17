package cn.gaocy.ktpractice.chapter06_type_system.deal_empty

import org.junit.Before
import org.junit.Test

fun main(args: Array<String>) {
    val len = test1(null)
    println("len=${len}")

    test2(null, "hello")
    test2("hello", "world")
//    test3(null)
    test3("非空断言!!")
    test4()
    test5(null)

    test6(null)
//    test7(null)   //报错
    test7("hello")

    test8()
}

/**
 *  ?.非空就执行
 */
fun test1(str: String?): Int {
//    return str?.length ?: 0
    return if (str == null) {
        0
    } else {
        str?.length//
    }
}

//elvis 运算符
fun test2(str1: String?, str2: String?) {
    println("${str1 ?: str2}")
}

//安全类型转换 as?
class Student constructor(val name: String, val age: Int) {
    override fun equals(other: Any?): Boolean {
        val stu = other as? Student ?: return false
        return stu.name == this.name && stu.age == this.age
    }

    override fun hashCode(): Int {
        return name.hashCode() * 31 + age
    }
}

//非空断言
fun test3(str: String?) {
    val temp: String = str!!
    println("temp = ${temp}")
}

/*  如果将一个可空值传递给一个非空的参数:
let 函数:
和安全调用运算符一起，它允许你对表达式求值，
检查求值结果是否为 null ，并把结果保存为一个变量。
所有这些动作都在同一个简洁的表达式中。
*/

fun test4() {
    getEmail("gaocy1213@gmail")?.let {
        sendEmail(it)
    }

    getEmail("gaocy1213")?.let {
        sendEmail(it)
    }
}

fun getEmail(email: String): String? {
    //kt 中暂时没有发现java中的三目运算符: boolean? 结果1:结果2
    return if (email.contains('@'))
        email
    else
        null
}

fun sendEmail(email: String) {
    println("send email to ${email}")
}

/**
 * 延迟初始化:    属性最终是非空的，但不能使用非空值在构造方法中初始化 。
 * 例如，在Android 中， Activity 的初始化就发生在 onCreate 方法中。
 * Jnit 则要求你把初始化的逻辑放在用＠ Before 注解的方法中 。
 */
class Cup {
    fun say() {
        println("I am a cup")
    }
}

/*延迟初始化的属性都是var，因为需要在构造方法外修改它的值，而val属性会被编译成必须在构造方法中初始化的 final 字段 。
尽管这个属性是非空类型，但是你不需要在构造方法 中初始化它。*/
class MyTest {
    private lateinit var cup: Cup
    @Before
    fun init() {
        cup = Cup();
    }

    @Test
    fun testCup() {
        cup.say()
    }
}

//========= 可空类型的扩展 ================
fun test5(str: String?) {
//    println("${str.isEmpty()}")   //报错
//    println("${str.isBlank()}")   //报错
    println("${if (str.isNullOrEmpty()) "str为空" else "str不为空"}")//str 参数即使为空,也能够调用该函数
}

// 泛型类型的参数 可以为空，类型参数 T 推导出的类型是可空类型 Any?
fun <T> test6(t: T) {
    println("t=${t}")
}

// 泛型类型的参数,指定了上界,不能为空
fun <T : Any> test7(t: T) {
    println("t=${t}")
}

//kt和java互调,可能出现的可空性
fun test8() {
    val obj: MayEmpty = MayEmpty()
    val str = obj.say(null)//实际上把Java返回给kotlin的值,当作了 类型! 处理
    println("test8 str = ${str}")//     test8 str = null
//    println("test8 str = ${str.length}")//    NullPointerException
}




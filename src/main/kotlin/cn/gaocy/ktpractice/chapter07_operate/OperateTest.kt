package cn.gaocy.ktpractice.chapter07_operate

fun main(args: Array<String>) {
    val point1 = Point(2, 3)
    val point2 = Point(4, 5)
    val point = point1 + point2
    println("point=${point}")

    val scale = Scale(2)
    println("point=${point * scale}")
    println("point[0]=${point[0]},point[1]=${point[1]}")

    val mutablePoint = MutablePoint(30, 34)
    mutablePoint.set(0, 12)
    println("mutablePoint=${mutablePoint}")

    val (x, y) = Point(4, 5)
    println("x=$x,y=$y")

    val (year, month, day) = Data(2020, 2, 26)
    println("year=$year")

    jieGouShengMing()

    val p = Person("gcy")
    println("p.emails=${p.emails}")
}

class Scale constructor(val times: Int)

class Point constructor(val x: Int, val y: Int) {

    override fun toString(): String {
        return "[x=${x},y=${y}]"
    }

    /*对于数据类，编译器为每个在主构造方法中声明的属性生成一个 componentN函数。
    下面的方式显示了如何手动为非数据类声明这些功能 ：*/
    operator fun component1() = x

    operator fun component2() = y

    operator fun plus(point: Point): Point {
        val resultX = this.x + point.x
        val resultY = this.y + point.y
        return Point(resultX, resultY)
    }
}

operator fun Point.times(scale: Scale): Point {
    val resultX = this.x * scale.times
    val resultY = this.y * scale.times
    return Point(resultX, resultY)
}

//重载一元运算符,取反
operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

//get 和 set 重载
operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

class MutablePoint constructor(var x: Int, var y: Int) {
    override fun toString(): String {
        return "[x=${x},y=${y}]"
    }
}

operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class Data(var year: Int, var month: Int, var day: Int) {

}

fun jieGouShengMing() {
    val mapOf = mapOf<String, String>("1" to "A", "2" to "B")
    for ((key, value) in mapOf) {
        print("[key=$key,value=$value],")
    }
}

/*
    使用委托属性:惰性初始化和 by lazy
lazy 函数返回一个对象，该对象具有一个名为 getValue 且签名正确的方法，
因此可以把它与 by 关键字一起使用来创建一个委托属性。
lazy 的参数是一个lambda ，可以调用它来初始化这个值.
默认情况下, lazy函数是线程安全的，如果需要，可以设置其他选项来告诉它要使用哪个锁，
或者完全避开同步，如果该类永远不会在多线程环境中使用。*/
class Person(val name: String) {
    val emails: String by lazy {
        loadEmail(name)
    }

    fun loadEmail(name: String) =
            if (name == "gcy") {
                "gaocy@gmail"
            } else {
                "unKnown"
            }
}
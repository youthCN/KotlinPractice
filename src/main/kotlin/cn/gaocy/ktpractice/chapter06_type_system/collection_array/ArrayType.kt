package cn.gaocy.ktpractice.chapter06_type_system.collection_array

fun main(args: Array<String>) {
    createArray1()
    createArray2()
    createArray3()

    createBasicTypeArray1()
}

//创建数组的3种方式:
fun createArray1() {
    val arrayOf = arrayOf(1, 2, 3)
    println("array=${arrayOf.joinToString("")}")
}

fun createArray2() {
    val arrayOf = arrayOfNulls<Int?>(10)
    arrayOf[0] = 1
    arrayOf[2] = 2
    println("array=${arrayOf.joinToString("")}")
}

/*
Array 构造方法接收数组的大小和一个 lambda 表达式，调用 lambda 表达式来创建每一个数组元素 。
这就是使用非空元素类型来初始化数组 ， 但不用显式地传递每个元素的方式 。
*/
fun createArray3() {
    val array = Array<String>(10) { size ->
        ('a' + size).toString()
    }
    println(array.joinToString(""))
}


//基本数据类型的数组的创建: IntArray ByteArray 、 CharArray 、 BooleanArray
fun createBasicTypeArray1(){
    val intArray = IntArray(5)//方式1
    println(intArray.joinToString (","))

    val intArrayOf = intArrayOf(0, 1, 2, 3)//方式1
    println(intArrayOf.joinToString (","))

    val intArray1 = IntArray(5) {//方式1
        it-> it*2
    }
    println(intArray1.joinToString (","))


}
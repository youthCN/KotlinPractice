package cn.gaocy.ktpractice.chapter06_type_system.collection_array

fun main(args: Array<String>) {
    val list = listOf<Int?>(1, null, 22, 5, 3, 7)
    val sum0 = validNum0(list)
    val sum1 = validNum1(list)
    println("sum0=${sum0}")
    println("sum1=${sum1}")

    //只读集合和可变集合
//    valList只有读取数据的方法,但是Java中不区分只读集合和可变集合,所以传递给Java后,不能继续保证集合中数据是否发生了变化
    val valList: Collection<Int> = arrayListOf<Int>(1, 2, 4, 5)

    val varList: MutableCollection<Int> = arrayListOf(1, 2, 3, 4)
    varList.add(5)//可以添加数据


}

//集合的类型可空
fun validNum0(list: List<Int?>): Int {
    var num = 0
    for (elem in list) {
        if (elem != null) {
            num++
        }
    }
    return num
}

fun validNum1(list: List<Int?>): Int {
    val resultList = list.filterNotNull()
    return resultList.size
}


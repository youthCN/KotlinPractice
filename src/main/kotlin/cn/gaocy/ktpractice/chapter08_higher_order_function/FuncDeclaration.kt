package cn.gaocy.ktpractice.chapter08_higher_order_function

fun main(args: Array<String>) {
    var canReturnNull: (Int, Int) -> Int? = { x, y ->
        println("x=$x,y=$y")
        null //注意不要使用return
    }
    canReturnNull(1, 2)

    var funOrNull: ((Int, Int) -> Int)? = null
    funOrNull?.invoke(2, 3)

    val arrays = arrayOf(1, 3, 4, 5, 6)

}

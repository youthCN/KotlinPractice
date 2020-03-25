package cn.gaocy.ktpractice.chapter11_coroutine

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
//    launch { // 在 runBlocking 作用域中启动一个新协程
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")

    coroutineScope {
        println("coroutineScope 1 ...")
        launch {
            delay(2000L)
            println("coroutineScope 3 ...")
        }
        println("coroutineScope 2 ...")
    }
    /*val job1 = GlobalScope.launch {
        println("coroutineScope 1 ...")
        delay(2000L)
        println("coroutineScope 2 ...")
    }*/
    println("end ...")
}
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
        coroutineScope {
            println("coroutineScope 4 ...")
            delay(2000L)
            println("coroutineScope 5 ...")
        }
    }
    /*val job1 = GlobalScope.launch {   在 GlobalScope 中启动的活动协程并不会使进程保活。它们就像守护线程。和coroutineScope不一样
        println("coroutineScope 1 ...")
        delay(2000L)
        println("coroutineScope 2 ...")
    }*/
    println("end ...")
}
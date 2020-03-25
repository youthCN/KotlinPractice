package cn.gaocy.ktpractice.chapter11_coroutine

import kotlinx.coroutines.*
fun main() = runBlocking{
    // this: CoroutineScope
    //这个launch 被一个 协程启动, 这个协程就是 runBlocking
    launch {
        println("Task from runBlocking is start")
        delay(200L)
        println("Task from runBlocking is end")
    }

    GlobalScope.launch {
        println("Task from GlobalScope launch1")
        delay(500L)
        println("Task from GlobalScope launch2")
    }

    coroutineScope {
        // Creates a coroutine scope
        launch {
            delay(5000L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
    }

    runBlocking {
        println("runBlocking  1111")
        delay(2000)
        println("runBlocking  2222")
    }

    println("Coroutine scope is over") // This line is not printed until the nested launch completes
}
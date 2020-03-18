package cn.gaocy.ktpractice.chapter11_coroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread


fun main() = runBlocking {
    launch {
        println("gcy")
        doWorld()
        println("xxx")
    }
    Thread.sleep(1000)
    println("Hello,")

    Thread {

    }
}

// 这是你的第一个挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}
package cn.gaocy.ktpractice.chapter11_coroutine

import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

/*
DEFAULT	立即执行协程体     DEFAULT 是饿汉式启动，launch 调用后，会立即进入待调度状态，一旦调度器 OK 就可以开始执行
ATOMIC	立即执行协程体，但在开始运行之前无法取消
UNDISPATCHED	立即在当前线程执行协程体，直到第一个 suspend 调用
LAZY	只有在需要的情况下运行     LAZY 是懒汉式启动，launch 后并不会有任何调度行为，协程体也自然不会进入执行状态，直到我们需要它执行的时候。
*/

suspend fun main() {
    log(1)
    val job1 = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
        log(2)
    }
    log(3)
    job1.join()
    log(4)
/*
打印结果： 2，3顺序可能不一致
22:16:39:250 [main] ---- 1
22:16:39:508 [main] ---- 3
22:16:39:537 [DefaultDispatcher-worker-1] ---- 2
22:16:39:539 [DefaultDispatcher-worker-1] ---- 4
*/

//    log(1)
//    val job2 = GlobalScope.launch(start = CoroutineStart.LAZY) {
//        log(2)
//    }
//    log(3)
//    job2.start()
//    //job2.join()
//    log(4)



}

val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] ---- $msg")

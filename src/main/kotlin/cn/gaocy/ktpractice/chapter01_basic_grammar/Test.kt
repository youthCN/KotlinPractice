package cn.gaocy.ktpractice.chapter01_basic_grammar

import java.util.*

class ZK constructor(val time: Long, val name: String) : Comparable<ZK> {
    override fun compareTo(other: ZK) = if (other.time > time) {
        1
    } else if (other.time < time) {
        -1
    } else {
        0;
    }

    override fun toString(): String {
        return "time=${time}, name=${name}"
    }
}

fun main() {
    val zk1 = ZK(1,"hah2")
    val zk2 = ZK(123,"hawxsxsh")
    val zk3 = ZK(1235,"hahcscs")
    val zk4 = ZK(12,"hahzac")
    val zk5 = ZK(13,"sdahah")
    val set = TreeSet<ZK>(compareBy { it.time  })
    set.add(zk1)
    set.add(zk2)
    set.add(zk3)
    set.add(zk4)
    set.add(zk5)
    println(set)
}

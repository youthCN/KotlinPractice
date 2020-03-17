package cn.gaocy.ktpractice.chapter04_class.classObj

class Outer{
    inner class Inner{
        fun getOuterRef(): Outer {
            return this@Outer
        }
        fun innerSay(){
            say()
        }
    }

    fun say():Unit{
       println("hello")
    }
}
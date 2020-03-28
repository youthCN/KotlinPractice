package cn.gaocy.ktpractice.chapter04_class.classObj

/**
 * object 关键字:   将 声明一个类 和 创建一个对象 结合起来
 *
 * 使用场景:
 *  1.对象声明(单例): 是定义单例的一种方式 Java中通过: 类名.INSTANCE.方法 调用KT中的方法
 *  2.伴生对象(类的静态方法): 可以持有工厂方法 和 其他与这个类相关,但在调用时并不依赖类实例的方法. 它们的成员可以通过类名来访问
 *  3.对象表达式: 用来代替Java的匿名内部类
 *
 */
//1.单例
object SingleInstance {
    fun say() {
        println("I am a SingleInstance")
    }
}

//2.伴生对象,不能被子类继承.相当于是静态方法,属于一个类
class A {
    companion object {
        fun say() {
            println("I am a companion object")
        }
    }
}

//伴生对象
class ThePerson(val name: String) {
    companion object Loader {
        fun formJson(json: String): ThePerson {
            return ThePerson(json)
        }

        @JvmStatic//可以在Java中使用类名直接访问
        fun say() {
            println("I am a companion object")
        }
    }

    override fun toString(): String {
        return name
    }
}

//伴生对象的拓展方法
class ThePerson2 constructor(val name: String, val age: Int) {
    companion object {//必须要存在伴生对象才能扩展

    }
}

//伴生对象的拓展方法,
fun ThePerson2.Companion.getPerson(name: String, age: Int): ThePerson2 {
    return ThePerson2(name, age)
}


//
interface Click {
    fun onClick()
}

//匿名内部类
class Button {
    fun setClick(click: Click) {
        click.onClick()
    }
}

fun main(args: Array<String>) {
    SingleInstance.say()
    A.say()
    val gcy = ThePerson.Loader.formJson("gcy")
    println(gcy)

    val bt = Button()
    bt.setClick(object : Click {
        //匿名内部类
        override fun onClick() {
            println("I am clicking")
        }
    })

    Thread(object: Runnable{
        override fun run() {

        }
    })

    Thread(Runnable{
        fun run() {

        }
    })

    Thread({

    })

    Thread(){

    }


}
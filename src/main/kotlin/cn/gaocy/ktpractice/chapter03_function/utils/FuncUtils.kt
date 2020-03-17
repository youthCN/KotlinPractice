//@file:JvmName("RenameClass")//会生成这个名字的类名
package cn.gaocy.ktpractice.chapter03_function.utils

import java.lang.StringBuilder

/**
 * 顶层函数将会被编译成类的静态方法
 */
@JvmOverloads//java 调用时,会生成几个参数的重载方法
fun <T> joinToString(coll: Collection<T>, sep: String = ",", prefix: String = "[", postfix: String = "]"): String {
    var result = StringBuilder(prefix);
    for ((index, element) in coll.withIndex()) {
        if (index > 0) {
            result.append(sep)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
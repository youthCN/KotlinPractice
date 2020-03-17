package cn.gaocy.ktpractice.chapter03_function.localfunc

import java.lang.IllegalArgumentException

/**
 * 在方法中嵌套一个 局部函数,用来检测变量值是否合法
 */
class User(val id: Int, val name: String, val address: String) {
    fun save(user: User) {
        checkBeforeSave()
        //保存数据
        println("""save Name=$name, Address =${address}""")
    }

    fun User.checkBeforeSave() {
        fun validate(value: String, fieldName: String) {
            if (name.isEmpty()) {
                throw IllegalArgumentException("""can`t save ${fieldName},value is empty""")
            }
        }
        validate(name, "Name")
        validate(address, "Address")
    }
}
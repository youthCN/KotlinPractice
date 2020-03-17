package cn.gaocy.ktpractice.chapter04_class.classObj

/**
 * 数据对象会重写 hashCode, equals ,toString 方法,同时会添加 copy 方法
 */
data class Client(val name: String, val postalCode: Int) {

}

fun main(args: Array<String>) {
    val client = Client("gcy", 1)
    val copy = client.copy("zjy", 2)
    println("""copy=${copy}""")
}
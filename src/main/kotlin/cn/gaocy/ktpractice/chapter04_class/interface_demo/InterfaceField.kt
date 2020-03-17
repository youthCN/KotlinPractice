package cn.gaocy.ktpractice.chapter04_class.interface_demo

interface FiledInterface {
    var mail: String
    val name: String
        get() {
            return """${mail.substringBefore("@")}"""
        }
//    get() ="""${mail.substringBefore("@")}"""//

}

class MyFiled : FiledInterface {
    override var mail: String = ""
}

class MyFiled1(override var mail: String) : FiledInterface {}
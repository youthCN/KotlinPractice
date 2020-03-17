package cn.gaocy.ktpractice.chapter04_class.classObj

class User constructor(name: String) {
    private var name: String

    init {
        this.name = name
    }
}

class User2(var name: String)

class User1(_name: String) {
    val name = _name
}

open class Person {
    lateinit var name: String
    private lateinit var sex: String
    private lateinit var address: String
    var age: Int
    get()= field
        set(value) {
            if (value < 0 || value > 200) {
                field = 0
            } else {
                field = value
            }
        }

    constructor(name: String, sex: String, address: String, age: Int) {
        this.name = name
        this.sex = sex
        this.address = address
        this.age = age
    }

    constructor(name: String, sex: String, address: String) : this(name, sex, address, 0)

    constructor(name: String, sex: String = "male") : this(name, sex, "beijing") {}

    fun say() {
        println("""name=${name},sex=${sex},address=${address}""")
    }

    override fun toString(): String {
        return """name=${name},sex=${sex},address=${address}"""
    }

}

class Student : Person {
    constructor(name: String, sex: String, address: String) : super(name, sex, address) {
    }
}
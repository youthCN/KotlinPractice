package cn.gaocy.ktpractice.chapter04_class.interface_demo

interface Clickable {
    fun click()
    fun showOff() {
        println("now I am showing")
    }
}

interface Focus {

    fun isShow(): Boolean;

    fun showOff() {
        println("Focus Showing")
    }
}

class MyButton : Clickable {
    override fun click() {
        println("I am clicked")
    }
}

class ShowButton : Clickable, Focus {
    override fun isShow(): Boolean {
        return true
    }

    override fun click() {
        println("I am clicked")
    }

    override fun showOff() {
       super<Clickable>.showOff()
//       super<Focus>.showOff()
    }

}

fun main(args: Array<String>) {
    var bt: MyButton = MyButton();
    bt.click()
    bt.showOff()

    var showButton = ShowButton()
    showButton.showOff()
}
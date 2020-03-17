package cn.gaocy.ktpractice.chapter04_class.classObj

/**
 * 密封类,表示一个类只有有限个子类
 */
sealed class Expr {
    class Num(val num: Int) : Expr()

    class Sum(val left: Expr, val right: Expr) : Expr()

    fun evel(e: Expr): Int = when (e) {
        is Num -> {
            e.num
        }
        is Sum -> (evel(e.left) + evel(e.right))
    }

    fun evel2(e: Expr): Int {
        return when (e) {
            is Num -> {
                e.num
            }
            is Sum -> (evel(e.left) + evel(e.right))
        }
    }
}




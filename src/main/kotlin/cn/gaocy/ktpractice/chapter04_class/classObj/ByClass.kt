package cn.gaocy.ktpractice.chapter04_class.classObj

/**
 * 类 委托,使用by关键字,实现 装饰器模式 效果
 * 实现的接口,和构造方法传入的接口一样,达到统一接口的目的
 *类委托: by + 委托对象
 */
class CountSet<T>(val innerSet: MutableCollection<T> = HashSet()) : MutableCollection<T> by innerSet {
    var addCount = 0
    override fun add(element: T): Boolean {
        addCount++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        addCount += elements.size
        return addAll(elements)
    }

}
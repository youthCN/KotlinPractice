package cn.gaocy.ktpractice.chapter08_higher_order_function

fun main() {
 /*
 内联扩展函数之let
    let扩展函数的实际上是一个作用域函数，当你需要去定义一个变量在一个特定的作用域范围内，let函数的是一个不错的选择；
    let函数另一个作用就是可以避免写一些判断null的操作。

1、let函数的使用的一般结构
object.let{
   it.todo()//在函数体内使用it替代object对象去访问其公有的属性和方法
   ...
}
//另一种用途 判断object为null的操作
object?.let{//表示object不为null的条件下，才会去执行let函数体
   it.todo()
}

2、let函数底层的inline扩展函数+lambda结构
     @kotlin.internal.InlineOnly
public inline fun <T, R> T.let(block: (T) -> R): R = block(this)

3、let函数inline结构的分析
从源码let函数的结构来看它是只有一个lambda函数块block作为参数的函数,调用T类型对象的let函数，则该对象为函数的参数。
在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式。

4、let函数的kotlin和Java转化
 //kotlin
 fun main(args: Array<String>) {
    val result = "testLet".let {
        println(it.length)
        1000
    }
    println(result)
 }

 //java
 public final class LetFunctionKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      String var2 = "testLet";
      int var4 = var2.length();
      System.out.println(var4);
      int result = 1000;
      System.out.println(result);
   }
}

5、let函数适用的场景

场景一: 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。
场景二: 然后就是需要去明确一个变量所处特定的作用域范围内可以使用

6、let函数使用前后的对比

没有使用let函数的代码是这样的，看起来不够优雅
mVideoPlayer?.setVideoView(activity.course_video_view)
mVideoPlayer?.setControllerView(activity.course_video_controller_view)
mVideoPlayer?.setCurtainView(activity.course_video_curtain_view)

使用let函数后的代码是这样的
mVideoPlayer?.let {
	   it.setVideoView(activity.course_video_view)
	   it.setControllerView(activity.course_video_controller_view)
	   it.setCurtainView(activity.course_video_curtain_view)
}
    */
    val result = "testLet".let {
        println(it.length)
        1000
    }
    println("result = $result")


}
package cn.gaocy.ktpractice.chapter06_type_system.basic_type;

public class VoidTest<T> {
    T say(T t) {
        System.out.println("you input t=" + t);
        return t;
    }

    public static void main(String[] args) {
        VoidTest<Void> vt = new VoidTest();
        Void say = vt.say(null);
        System.out.println("say = " + say);
    }
}

//相较于kotlin,java必须有返回值
class Process extends VoidTest<Void> {
    @Override
    Void say(Void aVoid) {
        return null;
    }
}

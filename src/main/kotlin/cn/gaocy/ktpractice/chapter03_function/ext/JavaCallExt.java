package cn.gaocy.ktpractice.chapter03_function.ext;

public class JavaCallExt {
    public static void main(String[] args) {
        char helloLastChar = ExtFuncKt.lastChar("hello");
        System.out.println(helloLastChar);

        StringBuilder sb = new StringBuilder("Kotlin!");
        char lastChar = ExtFileKt.getLastChar(sb);
        System.out.println(lastChar);
    }
}

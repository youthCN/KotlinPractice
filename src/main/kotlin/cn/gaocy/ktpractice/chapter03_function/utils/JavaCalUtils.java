package cn.gaocy.ktpractice.chapter03_function.utils;

import java.util.ArrayList;
import java.util.List;

public class JavaCalUtils {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        String joinToString = FuncUtilsKt.joinToString(list);
        System.out.println(joinToString);

    }
}

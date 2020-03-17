package cn.gaocy.ktpractice.chapter06_type_system.deal_empty;

public class MayEmpty {
    String say(String str) {
        if (str == null) {
            return null;
        }
        return "MyEmpty obj say:" + str;
    }
}

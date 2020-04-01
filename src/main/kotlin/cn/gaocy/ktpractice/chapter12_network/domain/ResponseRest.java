package cn.gaocy.ktpractice.chapter12_network.domain;

public class ResponseRest<T> {
    private int code = -1;
    private String msg = MSG_FIELD;
    private T result;

    public static final String MSG_SUCCEED = "succeed";
    public static final String MSG_FIELD = "field";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

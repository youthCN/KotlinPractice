package cn.gaocy.ktpractice.chapter12_network.net;

/**
 */
public interface OnResultListener<T> {

    void onSuccess(int code, String msg, T result);

    void onFailed(int code, String errorMsg);
}

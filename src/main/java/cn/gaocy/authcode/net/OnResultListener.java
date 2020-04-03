package cn.gaocy.authcode.net;

/**
 */
public interface OnResultListener<T> {

    void onSuccess(int code, String msg, T result);

    void onFailed(int code, String errorMsg);
}

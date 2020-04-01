package cn.gaocy.ktpractice.chapter12_network.net.intercepter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright (c) 2019, 北京视达科科技有限责任公司 All rights reserved.
 * author：chongyang.gao
 * date：2019/9/13
 * description：
 */
public class HttpCommonInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();
    private Map<String, String> mUrlQueryParamsMap = new HashMap<>();

    public static final String TAG = HttpCommonInterceptor.class.getSimpleName();

    public HttpCommonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 添加新的参数，添加到url 中
        /*HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder()
        .scheme(oldRequest.url().scheme())
        .host(oldRequest.url().host());*/

        // 新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());

        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        //添加新的请求参数，到url中
        if (mUrlQueryParamsMap.size() > 0) {
            HttpUrl oldUrl = oldRequest.url();
            HttpUrl.Builder builder = oldUrl.newBuilder();
            for (Map.Entry<String, String> params : mUrlQueryParamsMap.entrySet()) {
                builder.addQueryParameter(params.getKey(), params.getValue());
            }
            HttpUrl newHttpUrl = builder.build();
            requestBuilder.url(newHttpUrl);
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addUrlQueryParams(String key, String value){
            mHttpCommonInterceptor.mUrlQueryParamsMap.put(key,value);
            return this;
        }

        public Builder addUrlQueryParams(String key, int value){
            return addUrlQueryParams(key, String.valueOf(value));
        }

        public Builder addUrlQueryParams(String key, float value){
            return addUrlQueryParams(key, String.valueOf(value));
        }

        public Builder addUrlQueryParams(String key, long value){
            return addUrlQueryParams(key, String.valueOf(value));
        }

        public Builder addUrlQueryParams(String key, double value){
            return addUrlQueryParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;
        }

    }
}
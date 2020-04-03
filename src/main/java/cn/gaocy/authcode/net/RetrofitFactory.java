package cn.gaocy.authcode.net;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitFactory {
    public static final String TAG = RetrofitFactory.class.getSimpleName();
    public static String BASE_URL = URLConstant.BASE_URL;
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    private static final int DEFAULT_READ_TIMEOUT = 30;
    private Retrofit retrofit;
    private HttpApi httpApi;

    private OkHttpClient.Builder okHttpBuilder;

    public static final String LOG_TAG = "HttpLog:  ";

    //构造方法私有
    private RetrofitFactory() {
        //创建 OkHttpClient 并配置
        okHttpBuilder = new OkHttpClient.Builder();
        /**
         * 请求头拦截器
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body());
//                requestBuilder.addHeader("Authorization", "Bearer " + BaseConstant.TOKEN);//添加请求头信息，服务器进行token有效性验证
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);

        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addUrlQueryParams(CommonParamsConstant.username, "gaocy")
                .addUrlQueryParams(CommonParamsConstant.password, "1213")
                .build();
        okHttpBuilder.addInterceptor(commonInterceptor);


        /**
         * 日志拦截器
         */
//        if (BuildConfig.DEBUG) {
        HttpLogInterceptor loggingInterceptor = new HttpLogInterceptor(new HttpLogInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println(LOG_TAG+ message);
            }
        });
        loggingInterceptor.setLevel(HttpLogInterceptor.Level.HEADERS);
        okHttpBuilder.addInterceptor(loggingInterceptor);
//        }

        /**
         * 设置超时和重新连接
         */
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true);

        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())//json转换成JavaBean
                .baseUrl(BASE_URL)
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();

    }

    //获取单例
    public static RetrofitFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取retrofit
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 获取httpService
     *
     * @return
     */
    public HttpApi getHttpApi() {
        return httpApi;
    }

}
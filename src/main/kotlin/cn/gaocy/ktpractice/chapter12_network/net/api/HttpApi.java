package cn.gaocy.ktpractice.chapter12_network.net.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 存放本本应用所有请求的Api
 */

public interface HttpApi {
    @GET("SmsServlet")
    Call<ResponseBody> queryAllSmsAuthCode(@QueryMap Map<String, String> map);

    @GET("SmsServlet")
    Call<ResponseBody> insertCode(@QueryMap Map<String, String> map);

    @GET("SmsServlet")
    Call<ResponseBody> querySmsAuthCode(@QueryMap Map<String, String> map);

}

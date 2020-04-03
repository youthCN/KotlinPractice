package cn.gaocy.authcode.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

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

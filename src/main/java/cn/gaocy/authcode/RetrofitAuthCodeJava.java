package cn.gaocy.authcode;

import cn.gaocy.authcode.domain.SmsAuthCode;
import cn.gaocy.authcode.domain.SmsRest;
import cn.gaocy.authcode.net.RetrofitFactory;
import cn.gaocy.authcode.net.HttpApi;
import cn.gaocy.authcode.utils.IOUtil;
import cn.gaocy.authcode.utils.TimeUtils;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetrofitAuthCodeJava {
    static final Object lock = new Object();

    public static void main(String[] args) {
        File file = new File("allCode.txt");
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("create file failed....");
                    Thread.sleep(1000);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (true) {
            String sendTime = String.valueOf(TimeUtils.getCurrentDayMillis());
            HttpApi httpApi = RetrofitFactory.getInstance().getHttpApi();
            Map<String, String> map = new HashMap<String, String>();
            map.put("func", "querySmsAuthCode");
            map.put("sendTime", sendTime);
            Call<ResponseBody> querySmsAuthCode = httpApi.querySmsAuthCode(map);
            querySmsAuthCode.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String resultJson;
                    try {
                        resultJson = response.body().string();
                        Gson gson = new Gson();
                        SmsRest smsRest = gson.fromJson(resultJson, SmsRest.class);
                        int code = smsRest.getCode();
                        if (code == 0) {
                            System.out.println("request succeed :)");
                            synchronized (lock) {
                                FileWriter fileWriter = null;
                                BufferedWriter bw = null;
                                try {
                                    fileWriter = new FileWriter(file);
                                    bw = new BufferedWriter(fileWriter);
                                    List<SmsAuthCode> result = smsRest.getResult();
                                    for (SmsAuthCode smsAuthCode : result) {
                                        String sendCodeNo = smsAuthCode.getSendCodeNo();
                                        String receiveCodeNo = smsAuthCode.getReceiveCodeNo();
                                        String currentTimePretty = TimeUtils.getCurrentTimePretty(smsAuthCode.getSendTime());
                                        String authCode = smsAuthCode.getAuthCode();
                                        String resultStr = currentTimePretty+"|"+receiveCodeNo+"|"+authCode;
                                        bw.write(resultStr);
                                        bw.newLine();
                                        bw.flush();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("request failed, write file error..."+e.toString());
                                } finally {
                                    IOUtil.closeQuietly(bw);
                                    IOUtil.closeQuietly(fileWriter);
                                }
                            }
                            return;
                        }
                        System.out.println("request failed, params error..."+smsRest.getMsg());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("request failed, parse error..."+e.toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println("request failed,network..."+t.toString());
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

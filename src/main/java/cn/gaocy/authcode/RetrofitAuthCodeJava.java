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
import java.io.IOException;
import java.util.*;

public class RetrofitAuthCodeJava {
    static final Object lock = new Object();
    private static Set<SmsAuthCode> allSmsAuthCode = new HashSet<>();
    private static FileWriter fileWriter;
    private static BufferedWriter bw = null;

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
        try {
            fileWriter = new FileWriter(file);
            bw = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("request failed, write file error..." + e.toString());
            try {
                Thread.sleep(1000);
                return;
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        final String sendTime = String.valueOf(System.currentTimeMillis());
//            String sendTime = String.valueOf(TimeUtils.getCurrentDayMillis());
        while (true) {
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
                            System.out.print(":) ");
                            synchronized (lock) {
                                try {
                                    List<SmsAuthCode> result = smsRest.getResult();
                                    int size = result.size();
                                    for (int i = 0; i < size; i++) {
                                        SmsAuthCode smsAuthCode = result.get(i);
                                        if (!allSmsAuthCode.contains(smsAuthCode)) {
                                            allSmsAuthCode.add(smsAuthCode);
                                            String sendCodeNo = smsAuthCode.getSendCodeNo();
                                            String receiveCodeNo = smsAuthCode.getReceiveCodeNo();
//                                            String currentTimePretty = TimeUtils.getCurrentTimePretty(smsAuthCode.getSendTime());
                                            String currentTimePretty = String.valueOf(i);
                                            String authCode = smsAuthCode.getAuthCode();
                                            String resultStr = currentTimePretty + "|" + receiveCodeNo + "|" + authCode;
                                            bw.write(resultStr);
                                            bw.newLine();
                                            bw.flush();
                                            System.out.println(resultStr);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("request failed, write file error..." + e.toString());
                                }
                            }
                            return;
                        }
                        System.out.println("request failed, params error..." + smsRest.getMsg());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("request failed, parse error..." + e.toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println("request failed,network..." + t.toString());
                }
            });
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

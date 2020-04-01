package cn.gaocy.ktpractice.chapter12_network.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SmsRest extends ResponseRest<List<SmsAuthCode>> {

    public SmsRest() {
        setResult(new ArrayList<SmsAuthCode>());
    }

    public void addSmsAuthCode(SmsAuthCode smsAuthCode) {
        if (smsAuthCode != null) {
            getResult().add(smsAuthCode);
        }
    }

    public void addSmsAuthCode(List<SmsAuthCode> smsAuthCodes) {
        if (smsAuthCodes != null) {
            getResult().addAll(smsAuthCodes);
        }
    }

    @Override
    public String toString() {
        return "ResponseRest{" +
                "code=" + getCode() +
                ", msg='" + getMsg() + '\'' +
                ", result='" + getResult() + '\'' +
                '}';
    }

    public String toJson() {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(this);
        return jsonStr;
    }


}

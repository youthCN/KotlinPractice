package cn.gaocy.authcode.domain;

import java.util.Objects;

public class SmsAuthCode {
    private int id;
    private String sendCodeNo;
    private String receiveCodeNo;
    private String authCode;
    private long sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendCodeNo() {
        return sendCodeNo;
    }

    public void setSendCodeNo(String sendCodeNo) {
        this.sendCodeNo = sendCodeNo;
    }

    public String getReceiveCodeNo() {
        return receiveCodeNo;
    }

    public void setReceiveCodeNo(String receiveCodeNo) {
        this.receiveCodeNo = receiveCodeNo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsAuthCode that = (SmsAuthCode) o;
        return getSendTime() == that.getSendTime() &&
                Objects.equals(getSendCodeNo(), that.getSendCodeNo()) &&
                Objects.equals(getReceiveCodeNo(), that.getReceiveCodeNo()) &&
                Objects.equals(getAuthCode(), that.getAuthCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSendCodeNo(), getReceiveCodeNo(), getAuthCode(), getSendTime());
    }

    @Override
    public String toString() {
        return "SmsAuthCode{" +
                "id=" + id +
                ", sendCodeNo='" + sendCodeNo + '\'' +
                ", receiveCodeNo='" + receiveCodeNo + '\'' +
                ", authCode='" + authCode + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }


}

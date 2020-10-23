package com.nemt.nemtj.wxbean;

import java.io.Serializable;

public class LoginInfo implements Serializable {
    public  String code;
    public  String sign;
    public  String iv;
    public  String  encry;
    public  String  raw;
    public  String deviceInfo;
    public  String  apiToken;

    public LoginInfo() {
    }

    public LoginInfo(String code, String sign, String iv, String encry, String raw, String deviceInfo, String apiToken) {
        this.code = code;
        this.sign = sign;
        this.iv = iv;
        this.encry = encry;
        this.raw = raw;
        this.deviceInfo = deviceInfo;
        this.apiToken = apiToken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getEncry() {
        return encry;
    }

    public void setEncry(String encry) {
        this.encry = encry;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}

package com.nemt.nemtj.wxbean;

import java.io.Serializable;

public class ApiToken  implements Serializable {
    public String appid;
    public  String appkey;
    public String  deviceInfo;


    public ApiToken() {
    }

    public ApiToken(String appid, String appkey, String deviceInfo) {
        this.appid = appid;
        this.appkey = appkey;
        this.deviceInfo = deviceInfo;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}

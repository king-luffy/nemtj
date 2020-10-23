package com.nemt.nemtj.model;

import java.util.Date;

/**
 * Created by Z on 2018/3/7.
 */
public class AesM {

    public int id;
    public String content;
    public String showkey;
    public Date createtime;

    public AesM() {
    }

    public AesM(int id, String content, String showkey, Date createtime) {
        this.id = id;
        this.content = content;
        this.showkey = showkey;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShowkey() {
        return showkey;
    }

    public void setShowkey(String showkey) {
        this.showkey = showkey;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

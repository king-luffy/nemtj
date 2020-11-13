package com.nemt.nemtj.wxbean;

import lombok.Data;

@Data
public class User {
    public int id;
    public String openid;
    public String nickname;
    public String avatar;
    public int gender;
    public String mobile;
    public int discount;
    public String member;
    public String membertime;
    public int score;
    public int subject_id;
    public String subject;
    public long membertimeNew;
    public long createtime;
    public long expiretime;
    public long expires_in;
    public int code;

    public User() {
    }

    public User(int id, String openid, String nickname, String avatar, int gender, String mobile, int discount, String member, String membertime, int score, int subject_id,String subject, long membertimeNew, long createtime, long expiretime, long expires_in, int code) {
        this.id = id;
        this.openid = openid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.gender = gender;
        this.mobile = mobile;
        this.discount = discount;
        this.member = member;
        this.membertime = membertime;
        this.score = score;
        this.subject_id = subject_id;
        this.subject = subject;
        this.membertimeNew = membertimeNew;
        this.createtime = createtime;
        this.expiretime = expiretime;
        this.expires_in = expires_in;
        this.code = code;
    }


}

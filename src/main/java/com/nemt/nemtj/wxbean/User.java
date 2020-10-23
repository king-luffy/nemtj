package com.nemt.nemtj.wxbean;

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
    public long membertimeNew;
    public long createtime;
    public long expiretime;
    public long expires_in;
    public int code;

    public User() {
    }

    public User(int id, String openid, String nickname, String avatar, int gender, String mobile, int discount, String member, String membertime, int score, int subject_id, long membertimeNew, long createtime, long expiretime, long expires_in, int code) {
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
        this.membertimeNew = membertimeNew;
        this.createtime = createtime;
        this.expiretime = expiretime;
        this.expires_in = expires_in;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMembertime() {
        return membertime;
    }

    public void setMembertime(String membertime) {
        this.membertime = membertime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public long getMembertimeNew() {
        return membertimeNew;
    }

    public void setMembertimeNew(long membertimeNew) {
        this.membertimeNew = membertimeNew;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(long expiretime) {
        this.expiretime = expiretime;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

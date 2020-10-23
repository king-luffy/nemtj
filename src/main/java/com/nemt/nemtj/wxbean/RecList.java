package com.nemt.nemtj.wxbean;

public class RecList {
    private int id;
    private String tag;
    private String avatar;
    private String name;
    private String district;
    private int val;
    private String year;
    private String[] type;
    private String major;
    private String majors;
    private String subjects;
    private int years;
    private String average;
    private String ranks;
    private String lowest;
    private String seating;
    private String subject;
    private String flag;

    public RecList() {
    }

    public RecList(int id, String tag, String avatar, String name, String district, int val, String year, String[] type, String major, String majors, String subjects, int years, String average, String ranks, String lowest, String seating, String subject, String flag) {
        this.id = id;
        this.tag = tag;
        this.avatar = avatar;
        this.name = name;
        this.district = district;
        this.val = val;
        this.year = year;
        this.type = type;
        this.major = major;
        this.majors = majors;
        this.subjects = subjects;
        this.years = years;
        this.average = average;
        this.ranks = ranks;
        this.lowest = lowest;
        this.seating = seating;
        this.subject = subject;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

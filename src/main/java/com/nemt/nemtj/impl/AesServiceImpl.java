package com.nemt.nemtj.impl;

import com.nemt.nemtj.model.AesM;
import com.nemt.nemtj.wxbean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Z on 2018/3/7.
 */
@Service
public class AesServiceImpl implements AesService {
    @Autowired
    private AesService aesService;

    @Override
    public AesM queryByContent(String content) {
        return aesService.queryByContent(content);
    }

    @Override
    public int add(String content, String showkey,Date createtime) {
        return aesService.add(content, showkey,createtime);
    }

    @Override
    public int addlicense(String customname, int number, String uuid, String mac, String ip, Date createtime, Date updatetime,boolean isfather) {
        return aesService.addlicense( customname,  number,  uuid,  mac,  ip,  createtime,  updatetime,isfather);
    }

    @Override
    public int adduserlicense(String customname, int number, String uuid, String mac, String ip, Date createtime, Date updatetime,boolean isfather) {
        return aesService.addlicense( customname,  number,  uuid,  mac,  ip,  createtime,  updatetime,isfather);
    }

    @Override
    public Integer gettotalcount() {
        return aesService.gettotalcount();
    }

    @Override
    public Integer getusedcount() {
        return aesService.getusedcount();
    }

    @Override
    public int updatelicense(String customname, int number, String uuid, String mac, String ip, Date createtime, Date updatetime) {
        return aesService.updatelicense(customname,number,uuid,mac,ip,createtime,updatetime);
    }

    @Override
    public Date getcreatetime() {
        return null;
    }

    @Override
    public int isExistUser(String openid) {
        return aesService.isExistUser(openid);
    }

    @Override
    public int addUser(User user) {
        return aesService.addUser(user);
    }

    @Override
    public User findUser(String openid) {
        return aesService.findUser(openid);
    }

    @Override
    public List<Map> getRecList() {
        return aesService.getRecList();
    }

    @Override
    public List<Map> getRecListBy(Map<String, Object> params) {
        return aesService.getRecListBy(params);
    }

    @Override
    public List<Map> getSchoolList(Map<String, Object> params) {
        return aesService.getSchoolList(params);
    }

    @Override
    public List<Map> getCateList(Map<String, Object> params) {
        return aesService.getCateList(params);
    }

    @Override
    public List<Map> getCateMaster(Map<String, Object> params) {
        return aesService.getCateMaster(params);

    }


}


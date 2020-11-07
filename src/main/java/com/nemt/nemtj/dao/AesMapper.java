package com.nemt.nemtj.dao;


import com.nemt.nemtj.model.AesM;
import com.nemt.nemtj.wxbean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AesMapper {

//    AesM queryByContent(Map data);
    AesM queryByContent(String data);

    int add(@Param("content") String content, @Param("showkey") String showkey, @Param("createtime") Date createtime);

    int addlicense(
            @Param("customname") String customname, @Param("number") int number,
            @Param("uuid") String uuid, @Param("mac") String mac,
            @Param("ip") String ip,
            @Param("createtime") Date createtime,
            @Param("updatetime") Date updatetime,
            @Param("isfather") boolean isfather

    );

    int adduserlicense(
            @Param("customname") String customname, @Param("number") int number,
            @Param("uuid") String uuid, @Param("mac") String mac,
            @Param("ip") String ip,
            @Param("createtime") Date createtime,
            @Param("updatetime") Date updatetime,
            @Param("isfather") boolean isfather

    );

    Integer gettotalcount();

    Integer getusedcount();


    int updatelicense(
            @Param("customname") String customname, @Param("number") int number,
            @Param("uuid") String uuid, @Param("mac") String mac,
            @Param("ip") String ip,
            @Param("createtime") Date createtime,
            @Param("updatetime") Date updatetime


    );

    Date getcreatetime();

    int isExistUser(String openid);

    int addUser(User user);

    int updateUser(User user);

    User findUser(String openid);

    List<Map> getRecList();

    List<Map> getRecListBy(Map<String,Object> params);

    List<Map> getSchoolList(Map<String,Object> params);

    List<Map> getCateList(Map<String,Object> params);

    List<Map> getCateMaster(Map<String,Object> params);

}
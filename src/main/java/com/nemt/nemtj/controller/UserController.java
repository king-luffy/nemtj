package com.nemt.nemtj.controller;

import com.alibaba.fastjson.JSON;
import com.nemt.nemtj.dao.AesMapper;
import com.nemt.nemtj.util.JedisPoolUtil;
import com.nemt.nemtj.wxbean.LoginInfo;
import com.nemt.nemtj.wxbean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

import static com.nemt.nemtj.util.HttpUtil.HttpClientGet;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AesMapper aesMapper;

    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis=jedisPool.getResource();

    @RequestMapping(path = "/user/login", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(@RequestBody LoginInfo loginInfo) throws Exception {
        Map<Object,Object> map_userinfo=(Map<Object, Object>) JSON.parse(loginInfo.getRaw());
        String code=loginInfo.getCode();
        String url="https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> map=new HashMap<>();
        map.put("appid","wxc4ff33a549c67ab8");
        map.put("secret","35a7eeb048daf16c6ff4acb4fd1fa043");
        map.put("js_code",code);
        map.put("grant_type","authorization_code");

        String res=HttpClientGet(url,map);


        Map <Object,Object> map_res=(Map<Object, Object>) JSON.parse(res);
        String openid=map_res.get("openid").toString();

        int isexist=aesMapper.isExistUser(openid);
        Date date=new Date();

        if(isexist==0){

            User user=new User();
            user.setOpenid(openid);
            user.setNickname(map_userinfo.get("nickName").toString());
            user.setAvatar(map_userinfo.get("avatarUrl").toString());
            user.setGender(Integer.parseInt(map_userinfo.get("gender").toString()));
            user.setMobile("");
            user.setDiscount(0);
            user.setMember("0");
            user.setMembertime("");
            user.setScore(999);
            user.setSubject_id(13);
            user.setMembertimeNew(0);
            user.setCreatetime(date.getTime());
            user.setExpiretime(0);
            user.setExpires_in(0);
            user.setCode(0);
            aesMapper.addUser(user);
        }

        String uuid= UUID.randomUUID().toString();
        jedis.set(uuid,openid);
        Map map1=new HashMap();
        map1.put("code",0);
        map1.put("time",date.getTime());
        map1.put("msg","");

        User user=aesMapper.findUser(openid);
        Map map_return=new HashMap();
        map_return.put("id",user.getId());
        map_return.put("nickname",user.getNickname());
        map_return.put("avatar",user.getAvatar());
        map_return.put("gender",user.getGender());
        map_return.put("mobile",user.getMobile());
        map_return.put("discount",user.getDiscount());
        map_return.put("member",user.getMember());
        map_return.put("membertime",user.getMembertime());
        map_return.put("score",user.getScore());
        map_return.put("subject_id",user.getSubject_id());
        map_return.put("membertimeNew",user.getMembertimeNew());
        map_return.put("token",uuid);
        map_return.put("user_id",user.getId());
        map_return.put("createtime",user.getCreatetime());
        map_return.put("expiretime",user.getExpiretime());
        map_return.put("expires_in",user.getExpires_in());
        map_return.put("code",0);

        map1.put("data",map_return);


        String res_content=JSON.toJSONString(map1);


        return res_content;


        //return "success";
    }

    @RequestMapping(path = "/user/getUserInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserInfo(@RequestBody String data) throws Exception {
        //{"code":0,"msg":"SUCCESS","time":"1594100435","data":{"token":"f67303599f3c92b72c90d2061ae1dc02"}}

        Map map=(Map) JSON.parse(data);
        String usertoken=map.get("userToken").toString();
        String openid=jedis.get(usertoken);
        jedis.close();
        Map map1=new HashMap();
        Date date=new Date();
        map1.put("code",0);
        map1.put("time",date.getTime());
        map1.put("msg","");

        User user=aesMapper.findUser(openid);
        Map map_return=new HashMap();
        map_return.put("id",user.getId());
        map_return.put("nickname",user.getNickname());
        map_return.put("avatar",user.getAvatar());
        map_return.put("gender",user.getGender());
        map_return.put("mobile",user.getMobile());
        map_return.put("discount",user.getDiscount());
        map_return.put("member",user.getMember());
        map_return.put("membertime",user.getMembertime());
        map_return.put("score",user.getScore());
        map_return.put("subject_id",user.getSubject_id());
        map_return.put("membertimeNew",user.getMembertimeNew());
        map_return.put("token",usertoken);
        map_return.put("user_id",user.getId());
        map_return.put("createtime",user.getCreatetime());
        map_return.put("expiretime",user.getExpiretime());
        map_return.put("expires_in",user.getExpires_in());
        map_return.put("code",0);

        map1.put("data",map_return);


        String res_content=JSON.toJSONString(map1);


        return res_content;




    }

    @RequestMapping(path = "/user/exchangeNew", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String exchangeNew(@RequestBody String data) throws Exception {

        Map map=(Map) JSON.parse(data);
        String usertoken=map.get("userToken").toString();
        String openid=jedis.get(usertoken);
        jedis.close();
        Map map1=new HashMap();
        Date date=new Date();
        map1.put("code",0);
        map1.put("time",date.getTime());
        map1.put("msg","");

        User user=aesMapper.findUser(openid);
        if(user==null){
            map1.put("code",400);
            map1.put("msg","no user found");
            return JSON.toJSONString(map1);
        }
        Long membertimeNewL = new Date().getTime()+10000000L;
        user.setMembertimeNew(membertimeNewL);
        int res = aesMapper.updateUser(user);
        System.out.println(res);
        List<Map> maps2=new ArrayList<>();
        Map<String,Object> map2=new HashMap<>();
        maps2.add(map2);
        map2.put("membertimeNew",membertimeNewL);
        map1.put("data",maps2);

        return JSON.toJSONString(map1);

    }

    @RequestMapping(path = "/user/subject", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String subject(@RequestBody String data) throws Exception {

        User user = getUser(data);
        if(user==null){
            return buildNoUserResp();
        }

        Map map1=buildResp();
        String subject = getPara(data,"subject");
        user.setSubject(subject);
        int res = aesMapper.updateUser(user);
        System.out.println(res);

        setData(map1,"subject",subject);

        return JSON.toJSONString(map1);

    }

    @RequestMapping(path = "/user/getSubject", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSubject(@RequestBody String data) throws Exception {


        return "{\"code\":0,\"msg\":\"选择科目成功\",\"time\":\"1605250104\",\"data\":[]}";

    }

    private Map setData(Map data,String field,String value){
        List<Map> maps2 = (List)data.computeIfAbsent("data",d->new ArrayList<>());
        Map<String,Object> map2=new HashMap<>();
        maps2.add(map2);
        map2.put(field,value);
        return data;
    }

    private String getPara(String data,String field){
        Map map=(Map) JSON.parse(data);
        return map.get(field).toString();
    }

    private User getUser(String data){

        Map map=(Map) JSON.parse(data);
        String usertoken=map.get("userToken").toString();
        String openid=jedis.get(usertoken);
        jedis.close();
        return aesMapper.findUser(openid);
    }
    private String buildNoUserResp(){
        Map map1=new HashMap();
        map1.put("code",400);
        map1.put("time",new Date().getTime());
        map1.put("msg","no user found");
        return JSON.toJSONString(map1);
    }
    private Map buildResp(){
        Map map1=new HashMap();
        Date date=new Date();
        map1.put("code",0);
        map1.put("time",date.getTime());
        map1.put("msg","");
        return map1;
    }
}

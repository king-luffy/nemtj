package com.nemt.nemtj.controller;

import com.alibaba.fastjson.JSON;
import com.nemt.nemtj.dao.AesMapper;
import com.nemt.nemtj.impl.AesService;
import com.nemt.nemtj.model.AesM;
import com.nemt.nemtj.util.AESUtil;
import com.nemt.nemtj.util.JedisPoolUtil;
import com.nemt.nemtj.wxbean.ApiToken;
import com.nemt.nemtj.wxbean.LoginInfo;
import com.nemt.nemtj.wxbean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.nemt.nemtj.util.HttpUtil.HttpClientGet;

/**
 * @ProjectName: nemtj
 * @Package: com.nemt.nemtj.controller
 * @ClassName: NemtController
 * @Author: king_luffy_pc
 * @Description: ${description}
 * @Date: 2020/10/18 10:34 AM
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
public class NemtController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }


//    @Autowired
//    private AesService aesMapper;
    @Autowired
    private AesMapper aesMapper;

    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis=jedisPool.getResource();

    @RequestMapping(path = "/encrypt", method = RequestMethod.GET)
    @ResponseBody
    public String getEncrypt(String key, String content) throws Exception {
        Map map = new HashMap();
        String encryptData = AESUtil.encrypt(key, content);

        Long createtime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(format.format(createtime));
        String content_new = encryptData + createtime.toString();
        map.put("encryptData", content_new);
        aesMapper.add(content_new, key, date);

        return JSON.toJSONString(map);

    }

    @RequestMapping(path = "/decrypt", method = RequestMethod.POST)
    @ResponseBody
    public String getDecrypt(String key, String content) throws Exception {
        Map map = new HashMap();
        content = content.substring(0, content.length() - 13);
        map.put("decryptData", AESUtil.decrypt(key, content));
        return JSON.toJSONString(map);
    }

    //请求参数中包括+加号问题
    @RequestMapping(path = "/getKey", method = RequestMethod.GET)
    @ResponseBody
    public String getKey(String content) throws UnsupportedEncodingException {

        if (content.isEmpty()) {
            return "";
        }
        content = URLEncoder.encode(content, "UTF-8");
        content = URLEncoder.encode(content, "UTF-8");
        if (content.contains("%2B")) {
            content = content.replace("%2B", "^");
        }
        content = URLDecoder.decode(content, "UTF-8");
        content = URLDecoder.decode(content, "UTF-8");
        content = content.replace("^", "+");
        System.out.print(content);
        AesM a = aesMapper.queryByContent(content);
        if (a == null) {
            return "";
        }
        String key = a.getShowkey();
        return key;
    }


    @RequestMapping(path = "/security/v1/product/license/action/import_file", method = RequestMethod.POST)
    @ResponseBody
    public String importFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("password") String password) throws Exception {
        try {
//            String tokentmp = request.getHeader("Authorization");
//            String token = tokentmp.split(" ")[1];
//
//            boolean flag = LicenseInfo.validToken(token);

//            if (password.equals("sds@#$00123@$df)")) {
//                String key = "g23a56l8b012345x";
//                String content = new String(file.getBytes());
//                String ret = AESUtil.decrypt(key, content);
//                final License licenseSpec;
//                final BytesArray bytes = new BytesArray(ret.getBytes());
//                licenseSpec = License.fromSource(bytes, XContentType.JSON);
//                String customname = licenseSpec.customename();
//                int number = licenseSpec.number();
//                Integer count = aesMapper.gettotalcount();
//                if (count == null) {
//                    if (LicenseVerifier1.verifyLicense(licenseSpec)) {
//                        aesMapper.addlicense(customname, number, null, null, null, new Date(), null, true);
//                    }
//                } else {
//
//                    if (LicenseVerifier1.verifyLicense(licenseSpec)) {
//                        Date c = aesMapper.getcreatetime();
//                        aesMapper.updatelicense(customname, number, null, null, null, c, new Date());
//                    }
//                }
//                return "success";
//            } else {
                return "fail";
//            }
        }
        catch (Exception e){
            return "error";
        }
    }


    @RequestMapping(path = "/security/v1/product/license/action/import", method = RequestMethod.POST)
    @ResponseBody
    public String importLicense(HttpServletRequest request, @RequestParam("data") String data) throws Exception {

        try {
            String tokentmp = request.getHeader("Authorization");
            String token = tokentmp.split(" ")[1];

//            boolean flag = LicenseInfo.validToken(token);
//            if (flag == true) {
//                String key = "g23a56l8b012345x";
//                String ret = AESUtil.decrypt(key, data);
//                final License licenseSpec;
//                final BytesArray bytes = new BytesArray(ret.getBytes());
//                licenseSpec = License.fromSource(bytes, XContentType.JSON);
//                String customname = licenseSpec.customename();
//                int number = licenseSpec.number();
//                Integer count = aesMapper.gettotalcount();
//                if (count == null) {
//                    if (LicenseVerifier1.verifyLicense(licenseSpec)) {
//                        aesMapper.addlicense(customname, number, null, null, null, new Date(), null, true);
//                    }
//                } else {
//
//                    if (LicenseVerifier1.verifyLicense(licenseSpec)) {
//                        Date c = aesMapper.getcreatetime();
//                        aesMapper.updatelicense(customname, number, null, null, null, c, new Date());
//                    }
//                }
//                return "success";
//            } else {
                return "fail";
//            }
        }
        catch (Exception e){
            return  "error";
        }



    }


    @RequestMapping(path = "/security/v1/product/license/action/add", method = RequestMethod.POST)
    @ResponseBody
    public String addUserLicense(HttpServletRequest request, @RequestParam("resource") String resource, @RequestParam("ip") String ip, @RequestParam("uuid") String uuid, @RequestParam("mac") String mac) throws Exception {

        try {
            String tokentmp = request.getHeader("Authorization");
            String token = tokentmp.split(" ")[1];

//            boolean flag = LicenseInfo.validToken(token);
//            if (flag == true) {
//                int number = Integer.parseInt(resource);
//                int totallicense = aesMapper.gettotalcount();
//                int usedlicense = aesMapper.getusedcount();
//
//                if ((number + usedlicense) <= totallicense) {
//
//
//                    aesMapper.addlicense("inspur_customer", number, null, null, null, new Date(), null, false);
////                    LicenseInfo.generateByNumber(resource, ip);
//
//
//                    String licenseBase = "{\"license\":{\"issuer\":\"a\",\"issued_to\":\"a\",\"issue_date\":1526860800,\"max_nodes\":100,\"uid\":\"1f742aa9-068b-48b6-8b36-c36ff3a39e17\",\"start_date\":1526860800,\"expiry_date\":1992268768000,\"type\":\"platinum\",\"subscription_type\":\"platinum\",\"customer_name\":\"inspur\",\"product_model\":\"inspur\",\"number\":XXXX,\"product_serial\":\"inspur\"}}";
//                    String b = licenseBase.replace("XXXX", resource);
//                    final BytesArray bytes = new BytesArray(b.getBytes());
//                    License licenseSpec1 = License.fromSource(bytes, XContentType.JSON);
//                    License license = new LicenseSigner().sign(licenseSpec1);
//
//                    JSON json = JSON.parseObject(b);
//                    JSONObject jsonObject = JSON.parseObject(b);
//                    JSONObject jsonObject1 = jsonObject.getJSONObject("license");
//                    jsonObject1.put("signature", license.signature());
//                    String c = jsonObject.toJSONString();
//                    String url = "https://" + ip + "/api/license1";
//                    String loginurl = "https://" + ip + "/api/security/v1/login";
//                    String cc = "{\"username\":\"elastic\",\"password\":\"ZZ123zz!\"}";
//                    HttpClient httpClient = doPostLogin(loginurl, JSON.parseObject(cc));
//                    Thread.sleep(1000*10);
//                    String s = doPost(httpClient, url, jsonObject);
//
//                    return  "success";
//                }
//                else {
//                    return  "fail";
//                }
//
//
//            } else {
                return "fail";
//            }
        }
        catch (Exception e){
            return "error";
        }

    }


    @RequestMapping(path = "/security/v1/product/license/status", method = RequestMethod.GET)
    @ResponseBody
    public String getLicenseInfo(HttpServletRequest request) throws Exception {

        try {
            String tokentmp = request.getHeader("Authorization");
            String token = tokentmp.split(" ")[1];

//            boolean flag = LicenseInfo.validToken(token);
//            if (flag == true) {
//                int totallicense = aesMapper.gettotalcount();
//                int usedlicense = aesMapper.getusedcount();
//                Map<String, Object> map = new HashMap<>();
//                map.put("total", totallicense);
//                map.put("usage", usedlicense);
//                return JSON.toJSONString(map);
//            } else {
                return "fail";
//            }
        }
        catch (Exception e){
            return "error";
        }

    }


    @RequestMapping(path = "/security/v1/product/license/health", method = RequestMethod.GET)
    @ResponseBody
    public String getHealthInfo(HttpServletRequest request) throws Exception {

        return "success";
    }

//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public String login( String code, String sign, String iv, String encry, String raw, String deviceInfo, String apiToken) throws Exception {
//
//        String wx_code=code;
//        String appid="wxc4ff33a549c67ab8";
//        String app_secret="35a7eeb048daf16c6ff4acb4fd1fa043";
//
//        String wx_url="https://api.weixin.qq.com/sns/jscode2session";
//        Map<String,String> map=new HashMap<>();
//        map.put("appid",appid);
//        map.put("secret",app_secret);
//        map.put("js_code",wx_code);
//        map.put("grant_type","authorization_code");
//
//        String res=HttpClientGet(wx_url,map);
//        return res;
//
//
//        //return "success";
//    }

    @RequestMapping(path = "/user/login", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(@RequestBody LoginInfo loginInfo) throws Exception {
        Map <Object,Object> map_userinfo=(Map<Object, Object>) JSON.parse(loginInfo.getRaw());
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


    @RequestMapping(path = "/common/getapitoken", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getapitoken(@RequestBody ApiToken apiToken) throws Exception {
        //{"code":0,"msg":"SUCCESS","time":"1594100435","data":{"token":"f67303599f3c92b72c90d2061ae1dc02"}}
        Date date=new Date();
        String apitoken_str= DigestUtils.md5DigestAsHex(date.toString().getBytes());
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg","SUCCESS");
        map.put("time",date.getTime());

        Map map1=new HashMap();
        map1.put("token",apitoken_str);
        map.put("data",map1);
        return JSON.toJSONString(map);

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

    @RequestMapping(path = "/index/index", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getIndex(@RequestBody String data) throws Exception {

        String res="{\"code\":0,\"msg\":\"\",\"time\":\"1594102892\",\"data\":{\"indexList\":[{\"id\":29,\"title\":\"准考证打印通知\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200623\\/7d2c3da323e284da362040ef2c6776dd.png\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/6fGfpsAXj-idQvidRkxeNg\"},{\"id\":28,\"title\":\"高职本科分段\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200613\\/4bffe16dd919463c54b8096d8d70356d.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/hELFkqDnXj6fj-wJl74MWQ\"},{\"id\":20,\"title\":\"农村专项计划\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200613\\/a881c9650c1bf14e5558655c84091ca8.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/97FKdLeacvdChegjql9Fqw\"},{\"id\":7,\"title\":\"20\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200504\\/6c425fa4285e8393b19e55bdc7ddeb51.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/bc1qsxN14L_7YLWiSXQRfQ\"}],\"serveList\":[{\"id\":5,\"title\":\"常见问题大全\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200425\\/d761223fe8e71333a38687023110ae64.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/bc1qsxN14L_7YLWiSXQRfQ\"},{\"id\":3,\"title\":\"春考计划\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200425\\/796b06f7938667e64d353dd747beb5a3.jpg\",\"url\":\"\\/pages\\/school\\/spring_search\\/main\"},{\"id\":4,\"title\":\"志愿填报\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200425\\/fd634a8deaa77716df81b953509dba3f.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?id=7\"}],\"articleList\":[{\"id\":25,\"title\":\"公安、消防、军校分数线看这！\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/k71KJo0dALJWeRwptCOh3g\",\"description\":\"1\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/default-img.jpg\"}],\"adNews\":[{\"id\":123,\"title\":\"20年高考作文\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/2PSgeQh8YQr0UzcbWY8eBg\",\"description\":\"全国合集\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200707\\/2af2a60afaa0b22f1ed6db567d47e5b5.jpg\"}],\"menuList\":[{\"id\":10,\"title\":\"查大学\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/632ea621cdbc876ace8633b657314824.jpg\",\"url\":\"\\/pages\\/school\\/search\\/main\"},{\"id\":12,\"title\":\"招生计划\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/e177bcf0f001d18dc63f8efd69f48421.jpg\",\"url\":\"\\/pages\\/major\\/plan\\/main\"},{\"id\":11,\"title\":\"查专业\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/7c2b4cef45c0280e448028adf038b3ae.jpg\",\"url\":\"\\/pages\\/major\\/main\"},{\"id\":13,\"title\":\"一分一段\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/58b5287d3c6efc3c99dad62a8d072dcc.jpg\",\"url\":\"\\/pages\\/inform\\/news\\/main\"},{\"id\":24,\"title\":\"单招\\/综评\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200526\\/f8cc55c56a27315c70c9f93683d6fb37.jpg\",\"url\":\"\\/pages\\/major\\/plan\\/main3\"},{\"id\":36,\"title\":\"计算器\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/9f5215034bf5ba2698538c3a3bd8ae8e.jpg\",\"url\":\"\\/pages\\/mine\\/tool\\/main\"},{\"id\":35,\"title\":\"艺体类\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/4a57a7aa34c8cbe49ea22c07ba94887a.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?id=120\"},{\"id\":34,\"title\":\"学科评估\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/cbbf959a0319a385892ed5c93eb17599.jpg\",\"url\":\"\\/pages\\/major\\/subject\\/main\"},{\"id\":33,\"title\":\"留学\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/377c56530a494f78bf399e0123fba784.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=http:\\/\\/ks.sdmengdao.com\\/web\\/liuxue\\/\"},{\"id\":32,\"title\":\"高考估分\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/ca9ce45c5384929e5cd62393c72ff914.jpg\",\"url\":\"\\/pages\\/question\\/main\"}],\"policyList\":[{\"id\":125,\"title\":\"53万人参加高考\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Ney5MGXvRincNEf-0haNPA\",\"description\":\"不准提前交卷\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200705\\/e9371a01232c11d7f7fca536cb7ca0c2.jpg\"},{\"id\":117,\"title\":\"准考证打印通知\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/6fGfpsAXj-idQvidRkxeNg\",\"description\":\"准考证\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200623\\/7d2c3da323e284da362040ef2c6776dd.png\"},{\"id\":113,\"title\":\"10所高校2020在山东省综合评价招生简章汇总（全）\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/UDqm0yEp0TD3ZiLEWNQWww\",\"description\":\"招生简章\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200615\\/f21f419f2057798ddaeafa48aae4d00b.jpg\"},{\"id\":108,\"title\":\"高考缴费+等级考选报，夏季高考生要注意了，常见问题和流程图你看了吗？\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Z3NeyXxVKCxZEKEhvrQuCQ\",\"description\":\"缴费选报\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200524\\/7f7766c35dd7dcc24c77c9757260cb16.png\"},{\"id\":79,\"title\":\"全面开学！山东各学段复学复课时间确定！\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Ydrxk_y-oV1AiHwqh7dvyQ\",\"description\":\"开学时间\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200514\\/6900b4de8c135c40a556edd25fda7a77.png\"},{\"id\":77,\"title\":\"高考6选3时间定了！还不需要交钱!\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Jf20E_a_6NbTeBRq_DDWbw\",\"description\":\"选科通知\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200512\\/1e504421fe795807d6377e613d203bac.jpg\"},{\"id\":75,\"title\":\"高考缴费通知，只缴语数外，附带缴费流程图！\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/r1WI1g6WCZWWr-7L-2-BfQ\",\"description\":\"缴费通知\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200512\\/fd3604986d5971d282bc260db96bed31.jpg\"},{\"id\":71,\"title\":\"单招\\/综合评价报名时间已公布，附带招生计划表\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/T0g3Wv32WB1hP0Py2gnJvw\",\"description\":\"信息\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200506\\/1b173aeed807c26f6fee7556ea77b59d.jpg\"},{\"id\":60,\"title\":\"高考补报名通知\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/6Rjzh10lCX2cL7Vo7yt1Ng\",\"description\":\"报名\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200429\\/fb4cb7d65149b039770df0906f3c418c.png\"},{\"id\":59,\"title\":\"单招要进行网络考试了！山东春考、体考、艺考、单招等考试时间公布\",\"wechat_link\":\"http:\\/\\/ks.sdmengdao.com\\/web\\/liuxue\\/\",\"description\":\"政策\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200427\\/8938b70ec50b449d5ab140ea5b923445.png\"},{\"id\":52,\"title\":\"关注！山东高三开学后，走读生疫情防控如何做？\",\"wechat_link\":\"http:\\/\\/ks.sdmengdao.com\\/web\\/liuxue\\/\",\"description\":\"开学\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200414\\/2ba2321bf81a51237c2c04c9ee1b5037.png\"}],\"infoList\":[{\"id\":126,\"title\":\"山东十六地市考场安排\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/KY5lPVpdHlEVLG4DFv74eQ\",\"description\":\"仅供参考\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200704\\/a0a6bba8a7484e356ef2304efec5e23d.png\"},{\"id\":116,\"title\":\"一模二模划线汇总分析\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/eIn6VrarklFCfx1oePB5HQ\",\"description\":\"持续更新\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200616\\/9e50be4acdde97bf4e39104aebc32df5.jpg\"},{\"id\":106,\"title\":\"9科目全，答案全，可下载，潍坊二模试题+答案\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/k3YC3Fka5uX_WS1yEQGuYQ\",\"description\":\"试题\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200524\\/dab0ba4dd819742d1d02f2cf36910b54.png\"},{\"id\":105,\"title\":\"9科全！答案全！可下载！济南二模试题+答案\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/2jrM0xO1dMbx50oF2yQH0w\",\"description\":\"试题\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200524\\/8f6c64c94e346ce0bea0647818dd9c33.png\"},{\"id\":102,\"title\":\"学业水平考试通知\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/NYz5HXICeElR1xySCms_WQ\",\"description\":\"考试\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200514\\/6900b4de8c135c40a556edd25fda7a77.png\"},{\"id\":62,\"title\":\"已更新试题及获取方式\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/3af-7hraQfRUVuyh-A9w9w\",\"description\":\"考试信息\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200504\\/e9bc14d96a63848235a42165e22fab13.png\"},{\"id\":80,\"title\":\"济宁二模考试，试题+答案\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s?__biz=Mzg2NDE5NDIzNA==&mid=2247489873&idx=3&sn=4459c294e2818adaac92ae33a9f5b990&chksm=ce6c4284f91bcb92c5933e9a39b7dd2e983acb043cf12e0baf49f73cd88a5b6e63f1f947d0c7&token=2118490023&lang=zh_CN#rd\",\"description\":\"试题\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200514\\/662e0b126bf6e2d16178ffee534ba796.png\"},{\"id\":72,\"title\":\"高考电脑阅卷流程是这样的！认真读完说不定多拿20分\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/49j8Sa2qzFrqinddL4WCVg\",\"description\":\"小技巧\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200506\\/96df0e0e8ea84e0d5e72be50158260fc.png\"}],\"subject\":[],\"free\":0,\"consume\":2,\"title\":\"高考加油\",\"title2\":\"必胜！\",\"days\":\"000\",\"section\":\"40\",\"rank\":\"50000\",\"distance\":\"距离高考\"}}";

        return res;

    }

    @RequestMapping(path = "/school/getRecList", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRecList(@RequestBody String data) throws Exception {
        //List<Map> maps=aesMapper.getRecList();
        Map map=(Map) JSON.parse(data);
        int  val=Integer.parseInt(map.get("val").toString());
        int  section=Integer.parseInt(map.get("section").toString());
        String subject=map.get("subject").toString();
        String way=map.get("way").toString();
        String cate=map.get("cate").toString();
        String subjects=map.get("subjects").toString();


        int min_score=val-section;
        int max_score=val+section;
        int page=Integer.parseInt(map.get("page").toString());
        Map<String,Object>map1=new HashMap<>();

        boolean issite=map.containsKey("site");


        if(issite==true){
            String site=map.get("site").toString();//江苏
            String tab=map.get("tab").toString(); //985
            boolean istype=map.containsKey("type");
            if(istype==true){
                String type=map.get("type").toString(); //工科
                map1.put("type",type);
            }

            map1.put("site",site);
            map1.put("tab",tab);

        }



        map1.put("min_score",min_score);
        map1.put("max_score",max_score);
        map1.put("page",page);


        List<Map> maps1=aesMapper.getRecListBy(map1);

        List<Map> maps2=new ArrayList<>();

        for(int i=0;i<maps1.size();i++){
            Map<String,Object> map2=new HashMap<>();
            int id=Integer.parseInt(maps1.get(i).get("pid").toString());
            String tag="c";
            String avatar="https://image.tianziyuan.cn/logo/A743.jpg";
            String name=maps1.get(i).get("sch_name").toString();
            String district=maps1.get(i).get("sch_pro_short").toString();
            int val1=Integer.parseInt(maps1.get(i).get("sch_line").toString());
            String year="2017-2018";
            String type=maps1.get(i).get("stu_category").toString();
            String major="综合";
            String majors="综合";
            String subjects1="综合";
            String years=maps1.get(i).get("year_").toString();
            String  average="无平均数据";
            String ranks="";
            String lowest=maps1.get(i).get("sch_line").toString();
            String seating="47145";
            String subject1="理科";
            String flag="";
//            String flag="211";
            map2.put("id",id);
            map2.put("tag",tag);
            map2.put("avatar",avatar);
            map2.put("name",name);
            map2.put("district",district);
            map2.put("val",val1);
            map2.put("year",year);
            map2.put("type",type);
            map2.put("major",major);
            map2.put("majors",majors);
            map2.put("subjects",subjects1);
            map2.put("years",years);
            map2.put("average",average);
            map2.put("ranks",ranks);
            map2.put("lowest",lowest);
            map2.put("seating",seating);
            map2.put("subject",subject1);
            map2.put("flag",flag);
            maps2.add(map2);


        }


        Map map_r=new HashMap();
        Date date=new Date();
        map_r.put("code",0);
        map_r.put("time",date.getTime());
        map_r.put("msg","");

        Map map_return=new HashMap();
        map_return.put("list",maps2);
        map_return.put("total",208);

        String cwb="{\"c_min\":556,\n" +
                "\"c_max\":560,\n" +
                "\"w_min\":515,\n" +
                "\"w_max\":555,\n" +
                "\"b_min\":515,\n" +
                "\"b_max\":515}";

        //JSON.parse(cwb);

        map_return.put("cwb",JSON.parse(cwb));
        map_return.put("cwb2","理科综合");

        String article="[{\n" +
                "\t\t\"id\": 67,\n" +
                "\t\t\"title\": \"关于文科数据偏高的通知\",\n" +
                "\t\t\"wechat_link\": \"\",\n" +
                "\t\t\"description\": \"1\",\n" +
                "\t\t\"image\": \"https:\\/\\/image.tianziyuan.cn\\/default-img.jpg\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": 63,\n" +
                "\t\t\"title\": \"以下数据仅供参考\",\n" +
                "\t\t\"wechat_link\": \"\",\n" +
                "\t\t\"description\": \"提示\",\n" +
                "\t\t\"image\": \"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200427\\/8938b70ec50b449d5ab140ea5b923445.png\"\n" +
                "\t}\n" +
                "]";
        map_return.put("articleList",JSON.parse(article));


        map_r.put("data",map_return);

        String res_content=JSON.toJSONString(map_r);
        return res_content;

        //map_return.put("cwb",)





        //String test="{\"code\":0,\"msg\":\"\",\"time\":\"1594017512\",\"data\":{\"list\":[{\"id\":1103,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A743.jpg\",\"name\":\"青海大学\",\"district\":\"青海\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"综合\"],\"major\":\"预防医学\",\"majors\":\"化学或生物\",\"subjects\":\"|化学|生物|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47145\",\"subject\":\"理科\",\"flag\":\"211\"},{\"id\":1492,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A389.jpg\",\"name\":\"福建农林大学\",\"district\":\"福建\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"农业\"],\"major\":\"动物医学\",\"majors\":\"物理或化学或生物\",\"subjects\":\"|物理|化学|生物|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47370\",\"subject\":\"理科\",\"flag\":\"\"},{\"id\":910,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A475.jpg\",\"name\":\"河南大学\",\"district\":\"河南\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"综合\"],\"major\":\"英语（师范类）\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"581\",\"seating\":\"6127\",\"subject\":\"文科\",\"flag\":\"\"},{\"id\":289,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A332.jpg\",\"name\":\"苏州科技大学\",\"district\":\"江苏\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"工科\"],\"major\":\"物流管理(中外合作办学)（第四学年赴英国南威尔士大学就读，学费按该大学当年公布的收费标准执行）\",\"majors\":\"暂无选科数据\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47536\",\"subject\":\"理科\",\"flag\":\"\"},{\"id\":1155,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A426.jpg\",\"name\":\"青岛科技大学\",\"district\":\"山东\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"工科\"],\"major\":\"法学\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"556\",\"ranks\":\"66113\",\"lowest\":\"562\\/550\",\"seating\":\"13053\\/53060\",\"subject\":\"文\\/理\",\"flag\":\"0\"},{\"id\":1512,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A484.jpg\",\"name\":\"河南财经政法大学\",\"district\":\"河南\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"财经\"],\"major\":\"财政学类财政学、税收学）\",\"majors\":\"暂无选科数据\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"556\",\"ranks\":\"67761\",\"lowest\":\"565\\/547\",\"seating\":\"11834\\/55927\",\"subject\":\"文\\/理\",\"flag\":\"\"},{\"id\":723,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/B417.jpg\",\"name\":\"北京联合大学\",\"district\":\"北京\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"综合\"],\"major\":\"金融学（实验班）\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47555\",\"subject\":\"理科\",\"flag\":\"\"},{\"id\":723,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/B417.jpg\",\"name\":\"北京联合大学\",\"district\":\"北京\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"综合\"],\"major\":\"会计学（国际会计）\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47318\",\"subject\":\"理科\",\"flag\":\"\"},{\"id\":723,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/B417.jpg\",\"name\":\"北京联合大学\",\"district\":\"北京\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"综合\"],\"major\":\"人力资源管理\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47560\",\"subject\":\"理科\",\"flag\":\"\"},{\"id\":1512,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A484.jpg\",\"name\":\"河南财经政法大学\",\"district\":\"河南\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"财经\"],\"major\":\"人力资源管理\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"556\",\"ranks\":\"66877\",\"lowest\":\"564\\/548\",\"seating\":\"11902\\/54975\",\"subject\":\"文\\/理\",\"flag\":\"\"},{\"id\":371,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A637.jpg\",\"name\":\"重庆师范大学\",\"district\":\"重庆\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"师范\"],\"major\":\"生物科学（师范类）\",\"majors\":\"生物\",\"subjects\":\"|生物|\",\"years\":2019,\"average\":\"无平均数据\",\"ranks\":\"无和数据\",\"lowest\":\"556\",\"seating\":\"47282\",\"subject\":\"理科\",\"flag\":\"\"},{\"id\":1512,\"tag\":\"c\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/A484.jpg\",\"name\":\"河南财经政法大学\",\"district\":\"河南\",\"val\":556,\"year\":\"2017-2018\",\"type\":[\"财经\"],\"major\":\"外国语言文学类英语、商务英语）\",\"majors\":\"不限\",\"subjects\":\"|物理|化学|生物|地理|历史|政治|\",\"years\":2019,\"average\":\"556\",\"ranks\":\"65683\",\"lowest\":\"563\\/550\",\"seating\":\"12298\\/53385\",\"subject\":\"文\\/理\",\"flag\":\"\"}],\"total\":208,\"cwb\":{\"c_min\":556,\"c_max\":560,\"w_min\":515,\"w_max\":555,\"b_min\":515,\"b_max\":515},\"cwb2\":\"理科综合\",\"year\":2018,\"articleList\":[{\"id\":67,\"title\":\"关于文科数据偏高的通知\",\"wechat_link\":\"\",\"description\":\"1\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/default-img.jpg\"},{\"id\":63,\"title\":\"以下数据仅供参考\",\"wechat_link\":\"\",\"description\":\"提示\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200427\\/8938b70ec50b449d5ab140ea5b923445.png\"}]}}";


        // return test;

    }

    @RequestMapping(path = "/school/index", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSchoolIndex(@RequestBody String data) throws Exception {

        String a="{\"code\":0,\"msg\":\"\",\"time\":\"1594825539\",\"data\":{\"areaList\":[{\"district\":\"不限\"},{\"district\":\"北京\"},{\"district\":\"天津\"},{\"district\":\"河北\"},{\"district\":\"河南\"},{\"district\":\"山西\"},{\"district\":\"内蒙古\"},{\"district\":\"辽宁\"},{\"district\":\"吉林\"},{\"district\":\"湖南\"},{\"district\":\"黑龙江\"},{\"district\":\"四川\"},{\"district\":\"浙江\"},{\"district\":\"新疆\"},{\"district\":\"江西\"},{\"district\":\"江苏\"},{\"district\":\"上海\"},{\"district\":\"陕西\"},{\"district\":\"安徽\"},{\"district\":\"山东\"},{\"district\":\"贵州\"},{\"district\":\"重庆\"},{\"district\":\"\"},{\"district\":\"广西\"},{\"district\":\"云南\"},{\"district\":\"甘肃\"},{\"district\":\"湖北\"},{\"district\":\"福建\"},{\"district\":\"宁夏\"},{\"district\":\"广东\"},{\"district\":\"泰安,济南\"},{\"district\":\"海南\"},{\"district\":\"青海\"},{\"district\":\"西藏\"},{\"district\":\"保定\"},{\"district\":\"扬州\"},{\"district\":\"武汉\"},{\"district\":\"哈尔滨\"}],\"typeList\":[{\"type\":\"不限\"},{\"type\":\"综合\"},{\"type\":\"工科\"},{\"type\":\"财经\"},{\"type\":\"农业\"},{\"type\":\"林业\"},{\"type\":\"医药\"},{\"type\":\"师范\"},{\"type\":\"语言\"},{\"type\":\"体育\"},{\"type\":\"艺术\"},{\"type\":\"民族\"},{\"type\":\"政法\"},{\"type\":\"\"},{\"type\":\"医学\"}],\"tagList\":[\"不限\",\"985\",\"211\"],\"postList\":[\"不限\",\"本科\",\"专科\"],\"cbwList\":[\"不限\",\"冲\",\"保\",\"稳\"]}}";
        return a;
    }

    @RequestMapping(path = "/school/getSchoolList", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSchoolList(@RequestBody String data) throws Exception {
        //{"code":0,"msg":"SUCCESS","time":"1594100435","data":{"token":"f67303599f3c92b72c90d2061ae1dc02"}}

        Map map=(Map) JSON.parse(data);
        String area=map.get("area").toString();
        String type=map.get("type").toString();
        String tag=map.get("tag").toString();
        String post=map.get("post").toString();
        String keyword=map.get("keyword").toString();
        int page=Integer.parseInt(map.get("page").toString());


        Map<String,Object>map1=new HashMap<>();


        map1.put("area",area);
        map1.put("type",type);
        map1.put("tag",tag);
        map1.put("post",post);
        map1.put("keyword",keyword);







        map1.put("page",page);

        List<Map> maps1=aesMapper.getSchoolList(map1);

        List<Map> maps2=new ArrayList<>();

        for(int i=0;i<maps1.size();i++){
            Map<String,Object> map2=new HashMap<>();
            int id=Integer.parseInt(maps1.get(i).get("pid").toString());

            String avatar="https://image.tianziyuan.cn/logo/A743.jpg";
            String name=maps1.get(i).get("sch_name").toString();
            String district=maps1.get(i).get("city").toString();
            map2.put("id",id);
            map2.put("name",name);
            map2.put("avatar",avatar);
            map2.put("number","A251");
            map2.put("rank",0);
            map2.put("district",district);
            String array[]=new String[1];
            array[0]="211";
            map2.put("flags", array);
            String array1[]=new String[1];
            array1[0]="工科";
            map2.put("type", array1);
            map2.put("public","");
            maps2.add(map2);
        }



        Map map12=new HashMap();
        Date date=new Date();
        map12.put("code",0);
        map12.put("time",date.getTime());
        map12.put("msg","");




        map12.put("data",maps2);


        String res_content=JSON.toJSONString(map12);


        return res_content;




    }

    @RequestMapping(path = "/major/getIndex", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getMajorIndex(@RequestBody String data) throws Exception {

        String a="{\"code\":0,\"msg\":\"\",\"time\":\"1595069652\",\"data\":{\"one\":[{\"id\":1,\"name\":\"本科\",\"pid\":0,\"weigh\":1,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":2,\"name\":\"专科\",\"pid\":0,\"weigh\":2,\"createtime\":1588775505,\"updatetime\":1588775505}],\"two\":[{\"id\":33,\"name\":\"资源环境与安全\",\"pid\":2,\"weigh\":33,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":32,\"name\":\"公共管理与服务\",\"pid\":2,\"weigh\":32,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":31,\"name\":\"公安与司法\",\"pid\":2,\"weigh\":31,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":30,\"name\":\"教育与体育\",\"pid\":2,\"weigh\":30,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":29,\"name\":\"新闻传播\",\"pid\":2,\"weigh\":29,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":28,\"name\":\"文化艺术\",\"pid\":2,\"weigh\":28,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":27,\"name\":\"旅游\",\"pid\":2,\"weigh\":27,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":26,\"name\":\"财经商贸\",\"pid\":2,\"weigh\":26,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":25,\"name\":\"医药卫生\",\"pid\":2,\"weigh\":25,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":24,\"name\":\"电子信息\",\"pid\":2,\"weigh\":24,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":23,\"name\":\"交通运输\",\"pid\":2,\"weigh\":23,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":22,\"name\":\"食品药品与粮食\",\"pid\":2,\"weigh\":22,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":21,\"name\":\"轻工纺织\",\"pid\":2,\"weigh\":21,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":20,\"name\":\"生物与化工\",\"pid\":2,\"weigh\":20,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":19,\"name\":\"装备制造\",\"pid\":2,\"weigh\":19,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":18,\"name\":\"水利\",\"pid\":2,\"weigh\":18,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":17,\"name\":\"土木建筑\",\"pid\":2,\"weigh\":17,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":16,\"name\":\"能源动力与材料\",\"pid\":2,\"weigh\":16,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":15,\"name\":\"农林牧渔\",\"pid\":2,\"weigh\":15,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":14,\"name\":\"艺术学\",\"pid\":1,\"weigh\":14,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":13,\"name\":\"管理学\",\"pid\":1,\"weigh\":13,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":12,\"name\":\"医学\",\"pid\":1,\"weigh\":12,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":11,\"name\":\"农学\",\"pid\":1,\"weigh\":11,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":10,\"name\":\"工学\",\"pid\":1,\"weigh\":10,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":9,\"name\":\"理学\",\"pid\":1,\"weigh\":9,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":8,\"name\":\"历史学\",\"pid\":1,\"weigh\":8,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":7,\"name\":\"文学\",\"pid\":1,\"weigh\":7,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":6,\"name\":\"教育学\",\"pid\":1,\"weigh\":6,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":5,\"name\":\"法学\",\"pid\":1,\"weigh\":5,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":4,\"name\":\"经济学\",\"pid\":1,\"weigh\":4,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":3,\"name\":\"哲学\",\"pid\":1,\"weigh\":3,\"createtime\":1588775505,\"updatetime\":1588775505}],\"three\":[{\"id\":231,\"name\":\"安全类 \",\"pid\":33,\"weigh\":231,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":230,\"name\":\"环境保护类\",\"pid\":33,\"weigh\":230,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":229,\"name\":\"气象类\",\"pid\":33,\"weigh\":229,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":228,\"name\":\"金属与非金属矿类\",\"pid\":33,\"weigh\":228,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":227,\"name\":\"煤炭类\",\"pid\":33,\"weigh\":227,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":226,\"name\":\"石油与天然气类\",\"pid\":33,\"weigh\":226,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":225,\"name\":\"测绘地理信息类\",\"pid\":33,\"weigh\":225,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":224,\"name\":\"地质类（专科）\",\"pid\":33,\"weigh\":224,\"createtime\":1588775505,\"updatetime\":1588850126},{\"id\":223,\"name\":\"资源勘查类\",\"pid\":33,\"weigh\":223,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":222,\"name\":\"公共服务类\",\"pid\":32,\"weigh\":222,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":221,\"name\":\"公共管理类\",\"pid\":32,\"weigh\":221,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":220,\"name\":\"公共事业类\",\"pid\":32,\"weigh\":220,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":219,\"name\":\"司法技术类\",\"pid\":31,\"weigh\":219,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":218,\"name\":\"法律执行类\",\"pid\":31,\"weigh\":218,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":217,\"name\":\"法律实务类\",\"pid\":31,\"weigh\":217,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":216,\"name\":\"侦查类\",\"pid\":31,\"weigh\":216,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":215,\"name\":\"公安技术类\",\"pid\":31,\"weigh\":215,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":214,\"name\":\"公安指挥类\",\"pid\":31,\"weigh\":214,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":213,\"name\":\"公安管理类（专科）\",\"pid\":31,\"weigh\":213,\"createtime\":1588775505,\"updatetime\":1588850124},{\"id\":212,\"name\":\"体育类\",\"pid\":30,\"weigh\":212,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":211,\"name\":\"文秘类\",\"pid\":30,\"weigh\":211,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":210,\"name\":\"语言类\",\"pid\":30,\"weigh\":210,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":209,\"name\":\"教育类\",\"pid\":30,\"weigh\":209,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":208,\"name\":\"广播影视类\",\"pid\":29,\"weigh\":208,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":207,\"name\":\"新闻出版类\",\"pid\":29,\"weigh\":207,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":206,\"name\":\"文化服务类\",\"pid\":28,\"weigh\":206,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":205,\"name\":\"民族文化类\",\"pid\":28,\"weigh\":205,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":204,\"name\":\"表演艺术类\",\"pid\":28,\"weigh\":204,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":203,\"name\":\"艺术设计类\",\"pid\":28,\"weigh\":203,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":202,\"name\":\"会展类\",\"pid\":27,\"weigh\":202,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":201,\"name\":\"餐饮类\",\"pid\":27,\"weigh\":201,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":200,\"name\":\"旅游类\",\"pid\":27,\"weigh\":200,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":199,\"name\":\"物流类\",\"pid\":26,\"weigh\":199,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":198,\"name\":\"电子商务类（专科）\",\"pid\":26,\"weigh\":198,\"createtime\":1588775505,\"updatetime\":1588850122},{\"id\":197,\"name\":\"市场营销类\",\"pid\":26,\"weigh\":197,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":196,\"name\":\"工商管理类（专科）\",\"pid\":26,\"weigh\":196,\"createtime\":1588775505,\"updatetime\":1588850121},{\"id\":195,\"name\":\"经济贸易类\",\"pid\":26,\"weigh\":195,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":194,\"name\":\"统计类\",\"pid\":26,\"weigh\":194,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":193,\"name\":\"财务会计类\",\"pid\":26,\"weigh\":193,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":192,\"name\":\"金融类\",\"pid\":26,\"weigh\":192,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":191,\"name\":\"财政税务类\",\"pid\":26,\"weigh\":191,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":190,\"name\":\"健康管理与促进类\",\"pid\":25,\"weigh\":190,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":189,\"name\":\"人口与计划生育类\",\"pid\":25,\"weigh\":189,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":188,\"name\":\"公共卫生与卫生管理类\",\"pid\":25,\"weigh\":188,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":187,\"name\":\"康复治疗类\",\"pid\":25,\"weigh\":187,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":186,\"name\":\"医学技术类（专科）\",\"pid\":25,\"weigh\":186,\"createtime\":1588775505,\"updatetime\":1588850119},{\"id\":185,\"name\":\"药学类（专科）\",\"pid\":25,\"weigh\":185,\"createtime\":1588775505,\"updatetime\":1588850117},{\"id\":184,\"name\":\"护理类\",\"pid\":25,\"weigh\":184,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":183,\"name\":\"临床医学类（专科）\",\"pid\":25,\"weigh\":183,\"createtime\":1588775505,\"updatetime\":1588850116},{\"id\":182,\"name\":\"通信类\",\"pid\":24,\"weigh\":182,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":181,\"name\":\"计算机类（专科）\",\"pid\":24,\"weigh\":181,\"createtime\":1588775505,\"updatetime\":1588850114},{\"id\":180,\"name\":\"电子信息类（专科）\",\"pid\":24,\"weigh\":180,\"createtime\":1588775505,\"updatetime\":1588850109},{\"id\":179,\"name\":\"邮政类\",\"pid\":23,\"weigh\":179,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":178,\"name\":\"城市轨道交通类\",\"pid\":23,\"weigh\":178,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":177,\"name\":\"管道运输类\",\"pid\":23,\"weigh\":177,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":176,\"name\":\"航空运输类\",\"pid\":23,\"weigh\":176,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":175,\"name\":\"水上运输类\",\"pid\":23,\"weigh\":175,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":174,\"name\":\"道路运输类\",\"pid\":23,\"weigh\":174,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":173,\"name\":\"铁道运输类\",\"pid\":23,\"weigh\":173,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":172,\"name\":\"粮食储检类\",\"pid\":22,\"weigh\":172,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":171,\"name\":\"粮食工业类\",\"pid\":22,\"weigh\":171,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":170,\"name\":\"食品药品管理类\",\"pid\":22,\"weigh\":170,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":169,\"name\":\"药品制造类\",\"pid\":22,\"weigh\":169,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":168,\"name\":\"食品工业类\",\"pid\":22,\"weigh\":168,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":167,\"name\":\"纺织服装类\",\"pid\":21,\"weigh\":167,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":166,\"name\":\"印刷类\",\"pid\":21,\"weigh\":166,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":165,\"name\":\"包装类\",\"pid\":21,\"weigh\":165,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":164,\"name\":\"轻化工类\",\"pid\":21,\"weigh\":164,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":163,\"name\":\"化工技术类\",\"pid\":20,\"weigh\":163,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":162,\"name\":\"生物技术类\",\"pid\":20,\"weigh\":162,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":161,\"name\":\"汽车制造类\",\"pid\":19,\"weigh\":161,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":160,\"name\":\"航空装备类\",\"pid\":19,\"weigh\":160,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":159,\"name\":\"船舶与海洋工程装备类\",\"pid\":19,\"weigh\":159,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":158,\"name\":\"铁道装备类\",\"pid\":19,\"weigh\":158,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":157,\"name\":\"自动化类（专科）\",\"pid\":19,\"weigh\":157,\"createtime\":1588775505,\"updatetime\":1588850111},{\"id\":156,\"name\":\"机电设备类\",\"pid\":19,\"weigh\":156,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":155,\"name\":\"机械设计制造类\",\"pid\":19,\"weigh\":155,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":154,\"name\":\"水土保持与水环境类\",\"pid\":18,\"weigh\":154,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":153,\"name\":\"水利水电设备类\",\"pid\":18,\"weigh\":153,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":152,\"name\":\"水利工程与管理类\",\"pid\":18,\"weigh\":152,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":151,\"name\":\"水文水资源类\",\"pid\":18,\"weigh\":151,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":150,\"name\":\"房地产类\",\"pid\":17,\"weigh\":150,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":149,\"name\":\"市政工程类\",\"pid\":17,\"weigh\":149,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":148,\"name\":\"建设工程管理类\",\"pid\":17,\"weigh\":148,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":147,\"name\":\"建筑设备类\",\"pid\":17,\"weigh\":147,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":146,\"name\":\"土建施工类\",\"pid\":17,\"weigh\":146,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":145,\"name\":\"城乡规划与管理类\",\"pid\":17,\"weigh\":145,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":144,\"name\":\"建筑设计类\",\"pid\":17,\"weigh\":144,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":143,\"name\":\"建筑材料类\",\"pid\":16,\"weigh\":143,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":142,\"name\":\"非金属材料类\",\"pid\":16,\"weigh\":142,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":141,\"name\":\"有色金属材料类\",\"pid\":16,\"weigh\":141,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":140,\"name\":\"黑色金属材料类\",\"pid\":16,\"weigh\":140,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":139,\"name\":\"新能源发电工程类\",\"pid\":16,\"weigh\":139,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":138,\"name\":\"热能与发电工程类\",\"pid\":16,\"weigh\":138,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":137,\"name\":\"电力技术类\",\"pid\":16,\"weigh\":137,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":136,\"name\":\"渔业类\",\"pid\":15,\"weigh\":136,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":135,\"name\":\"畜牧业类\",\"pid\":15,\"weigh\":135,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":134,\"name\":\"林业类\",\"pid\":15,\"weigh\":134,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":133,\"name\":\"农业类\",\"pid\":15,\"weigh\":133,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":132,\"name\":\"设计学类\",\"pid\":14,\"weigh\":132,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":131,\"name\":\"美术学类\",\"pid\":14,\"weigh\":131,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":130,\"name\":\"戏剧与影视学类\",\"pid\":14,\"weigh\":130,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":129,\"name\":\"音乐与舞蹈学类\",\"pid\":14,\"weigh\":129,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":128,\"name\":\"艺术学理论类\",\"pid\":14,\"weigh\":128,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":127,\"name\":\"旅游管理类\",\"pid\":13,\"weigh\":127,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":126,\"name\":\"电子商务类（本科）\",\"pid\":13,\"weigh\":126,\"createtime\":1588775505,\"updatetime\":1588850014},{\"id\":125,\"name\":\"工业工程类\",\"pid\":13,\"weigh\":125,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":124,\"name\":\"物流管理与工程类\",\"pid\":13,\"weigh\":124,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":123,\"name\":\"图书情报与档案管理类\",\"pid\":13,\"weigh\":123,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":122,\"name\":\"公共管理类（本科）\",\"pid\":13,\"weigh\":122,\"createtime\":1588775505,\"updatetime\":1588850012},{\"id\":121,\"name\":\"农业经济管理类\",\"pid\":13,\"weigh\":121,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":120,\"name\":\"工商管理类（本科）\",\"pid\":13,\"weigh\":120,\"createtime\":1588775505,\"updatetime\":1588850008},{\"id\":119,\"name\":\"管理科学与工程类\",\"pid\":13,\"weigh\":119,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":118,\"name\":\"医学试验班类 \",\"pid\":12,\"weigh\":118,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":117,\"name\":\"医学试验班类\",\"pid\":12,\"weigh\":117,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":116,\"name\":\"护理学类\",\"pid\":12,\"weigh\":116,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":115,\"name\":\"医学技术类（本科）\",\"pid\":12,\"weigh\":115,\"createtime\":1588775505,\"updatetime\":1588850007},{\"id\":114,\"name\":\"法医学类\",\"pid\":12,\"weigh\":114,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":113,\"name\":\"中药学类\",\"pid\":12,\"weigh\":113,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":112,\"name\":\"药学类（本科）\",\"pid\":12,\"weigh\":112,\"createtime\":1588775505,\"updatetime\":1588850004},{\"id\":111,\"name\":\"中西医结合类\",\"pid\":12,\"weigh\":111,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":110,\"name\":\"中医学类\",\"pid\":12,\"weigh\":110,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":109,\"name\":\"公共卫生与预防医学类\",\"pid\":12,\"weigh\":109,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":108,\"name\":\"口腔医学类\",\"pid\":12,\"weigh\":108,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":107,\"name\":\"临床医学类（本科）\",\"pid\":12,\"weigh\":107,\"createtime\":1588775505,\"updatetime\":1588849989},{\"id\":106,\"name\":\"基础医学类\",\"pid\":12,\"weigh\":106,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":105,\"name\":\"草学类\",\"pid\":11,\"weigh\":105,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":104,\"name\":\"水产类\",\"pid\":11,\"weigh\":104,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":103,\"name\":\"林学类\",\"pid\":11,\"weigh\":103,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":102,\"name\":\"动物医学类\",\"pid\":11,\"weigh\":102,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":101,\"name\":\"动物生产类\",\"pid\":11,\"weigh\":101,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":100,\"name\":\"自然保护与环境生态类\",\"pid\":11,\"weigh\":100,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":99,\"name\":\"植物生产类\",\"pid\":11,\"weigh\":99,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":98,\"name\":\"公安技术类（本科）\",\"pid\":10,\"weigh\":98,\"createtime\":1588775505,\"updatetime\":1588849987},{\"id\":97,\"name\":\"生物工程类\",\"pid\":10,\"weigh\":97,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":96,\"name\":\"安全科学与工程类\",\"pid\":10,\"weigh\":96,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":95,\"name\":\"建筑类\",\"pid\":10,\"weigh\":95,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":94,\"name\":\"食品科学与工程类\",\"pid\":10,\"weigh\":94,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":93,\"name\":\"生物医学工程类\",\"pid\":10,\"weigh\":93,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":92,\"name\":\"环境科学与工程类\",\"pid\":10,\"weigh\":92,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":91,\"name\":\"林业工程类\",\"pid\":10,\"weigh\":91,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":90,\"name\":\"农业工程类\",\"pid\":10,\"weigh\":90,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":89,\"name\":\"核工程类\",\"pid\":10,\"weigh\":89,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":88,\"name\":\"兵器类\",\"pid\":10,\"weigh\":88,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":87,\"name\":\"航空航天类\",\"pid\":10,\"weigh\":87,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":86,\"name\":\"海洋工程类\",\"pid\":10,\"weigh\":86,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":85,\"name\":\"交通运输类\",\"pid\":10,\"weigh\":85,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":84,\"name\":\"轻工类\",\"pid\":10,\"weigh\":84,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":83,\"name\":\"纺织类\",\"pid\":10,\"weigh\":83,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":82,\"name\":\"矿业类\",\"pid\":10,\"weigh\":82,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":81,\"name\":\"地质类（本科）\",\"pid\":10,\"weigh\":81,\"createtime\":1588775505,\"updatetime\":1588849993},{\"id\":80,\"name\":\"化工与制药类\",\"pid\":10,\"weigh\":80,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":79,\"name\":\"测绘类\",\"pid\":10,\"weigh\":79,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":78,\"name\":\"水利类\",\"pid\":10,\"weigh\":78,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":77,\"name\":\"土木类\",\"pid\":10,\"weigh\":77,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":76,\"name\":\"计算机类（本科）\",\"pid\":10,\"weigh\":76,\"createtime\":1588775505,\"updatetime\":1588849997},{\"id\":75,\"name\":\"自动化类（本科）\",\"pid\":10,\"weigh\":75,\"createtime\":1588775505,\"updatetime\":1588850001},{\"id\":74,\"name\":\"电子信息类（本科）\",\"pid\":10,\"weigh\":74,\"createtime\":1588775505,\"updatetime\":1588850002},{\"id\":73,\"name\":\"电气类\",\"pid\":10,\"weigh\":73,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":72,\"name\":\"能源动力类\",\"pid\":10,\"weigh\":72,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":71,\"name\":\"材料类\",\"pid\":10,\"weigh\":71,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":70,\"name\":\"仪器类\",\"pid\":10,\"weigh\":70,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":69,\"name\":\"机械类\",\"pid\":10,\"weigh\":69,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":68,\"name\":\"力学类\",\"pid\":10,\"weigh\":68,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":67,\"name\":\"系统理论类\",\"pid\":9,\"weigh\":67,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":66,\"name\":\"理科试验班类\",\"pid\":9,\"weigh\":66,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":65,\"name\":\"统计学类\",\"pid\":9,\"weigh\":65,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":64,\"name\":\"心理学类\",\"pid\":9,\"weigh\":64,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":63,\"name\":\"生物科学类\",\"pid\":9,\"weigh\":63,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":62,\"name\":\"地质学类\",\"pid\":9,\"weigh\":62,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":61,\"name\":\"地球物理学类\",\"pid\":9,\"weigh\":61,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":60,\"name\":\"海洋科学类\",\"pid\":9,\"weigh\":60,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":59,\"name\":\"大气科学类\",\"pid\":9,\"weigh\":59,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":58,\"name\":\"地理科学类\",\"pid\":9,\"weigh\":58,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":57,\"name\":\"天文学类\",\"pid\":9,\"weigh\":57,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":56,\"name\":\"化学类\",\"pid\":9,\"weigh\":56,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":55,\"name\":\"物理学类\",\"pid\":9,\"weigh\":55,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":54,\"name\":\"数学类\",\"pid\":9,\"weigh\":54,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":53,\"name\":\"历史学类\",\"pid\":8,\"weigh\":53,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":52,\"name\":\"文科试验班类\",\"pid\":7,\"weigh\":52,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":51,\"name\":\"新闻传播学类\",\"pid\":7,\"weigh\":51,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":50,\"name\":\"外国语言文学类\",\"pid\":7,\"weigh\":50,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":49,\"name\":\"中国语言文学类\",\"pid\":7,\"weigh\":49,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":48,\"name\":\"职业技术教育类\",\"pid\":6,\"weigh\":48,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":47,\"name\":\"体育学类\",\"pid\":6,\"weigh\":47,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":46,\"name\":\"教育学类\",\"pid\":6,\"weigh\":46,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":45,\"name\":\"公安学类\",\"pid\":5,\"weigh\":45,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":44,\"name\":\"马克思主义理论类\",\"pid\":5,\"weigh\":44,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":43,\"name\":\"民族学类\",\"pid\":5,\"weigh\":43,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":42,\"name\":\"社会学类\",\"pid\":5,\"weigh\":42,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":41,\"name\":\"政治学类\",\"pid\":5,\"weigh\":41,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":40,\"name\":\"法学类\",\"pid\":5,\"weigh\":40,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":39,\"name\":\"经济与贸易类\",\"pid\":4,\"weigh\":39,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":38,\"name\":\"金融学类\",\"pid\":4,\"weigh\":38,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":37,\"name\":\"财政学类\",\"pid\":4,\"weigh\":37,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":36,\"name\":\"经济学类\",\"pid\":4,\"weigh\":36,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":35,\"name\":\"人文科学试验班类\",\"pid\":3,\"weigh\":35,\"createtime\":1588775505,\"updatetime\":1588775505},{\"id\":34,\"name\":\"哲学类\",\"pid\":3,\"weigh\":34,\"createtime\":1588775505,\"updatetime\":1588775505}]}}";

        Map map12=new HashMap();
        Date date=new Date();
        map12.put("code",0);
        map12.put("time",date.getTime());
        map12.put("msg","");


        String one="[\n" +
                "            {\n" +
                "                \"id\":1,\n" +
                "                \"name\":\"本科\",\n" +
                "                \"pid\":0,\n" +
                "                \"weigh\":1,\n" +
                "                \"createtime\":1588775505,\n" +
                "                \"updatetime\":1588775505\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":2,\n" +
                "                \"name\":\"专科\",\n" +
                "                \"pid\":0,\n" +
                "                \"weigh\":2,\n" +
                "                \"createtime\":1588775505,\n" +
                "                \"updatetime\":1588775505\n" +
                "            }\n" +
                "        ]";
        Map map=new HashMap();
        map.put("one",JSON.parse(one));

        //map12.put("data",maps2);
        return a;
    }

    @RequestMapping(path = "/major/getcate", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getMajorCate(@RequestBody String data) throws Exception {

        Map map=(Map) JSON.parse(data);
        String pid=map.get("pid").toString();
        String keyword=map.get("keyword").toString();
        return "";
    }

    @RequestMapping(path = "/school/getSchoolInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSchoolInfo(@RequestBody String data) throws Exception {
        String a="{\"code\":0,\"msg\":\"\",\"time\":\"1594818351\",\"data\":{\"id\":769,\"name\":\"江苏农牧科技职业学院\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/C806.jpg\",\"number\":\"C806\",\"type\":\"农业\",\"public\":null,\"position\":\"专科\",\"ranking\":0,\"district\":\"江苏\",\"flag\":\"\",\"man\":51,\"page\":\"6月26日更新\",\"page_wen\":\"6月26日更新\",\"info\":\"江苏农牧科技职业学院始建于1958年9月，其前身是“泰县农业中等技术学校”。1961年9月，更名为“江苏省泰州畜牧兽医学校”；1995年4月，更名为“江苏省畜牧兽医学校”；2001年6月，独立升格为“江苏畜牧兽医职业技术学院”；2013年3月，更名为“江苏农牧科技职业学院”。 \\n学院是我国东南沿海地区乃至我国南方14个省（市、自治区）唯一以培养农牧科技类技术技能型人才为主的高等院校，座落在京剧大师 梅兰芳的故乡——江苏省泰州市。 \\n2005年，学院以“优秀”等级在全省率先通过教育部高职高专人才培养水平评估；2008年，被确定为“江苏省示范性高等职业院校”；2010年，被教育部、财政部确定为“国家示范性高等职业院校建设计划”首批“国家骨干高职院建设单位”；2013年，以“优秀”等级通过骨干高职院校建设项目验收。从此，学院进入全国高职院校第一方阵，成为全国农业高职院校的排头兵。 \\n    学院占地面积4550余亩，校舍建筑面积55万平方米，形成了以凤凰路校区为主体、以江苏现代畜牧科技园和江苏中药科技园为两翼的“一主两翼”办学格局。学院拥有各级各类实验室、实训室200多个，建有江苏现代畜牧科技园、江苏中药科技园、江苏苏姜种猪有限公司、江苏倍康药业有限公司、教学动物医院、泰爱牧宠物医院等36个院内实训基地，校外实训实习基地700多个，图书馆藏书102万册，教学科研仪器设备总值1.54亿元。 \\n    学院面向全国25个省（市、自治区）招生，现有在校生近15000人。中国科学院院士、南开大学原校长饶子和担任学院名誉院长，国内外20多位知名教授包括6位两院院士担任学院兼职教授。学院现有教职工1007人，其中，副高以上职称教师202人，正教授52人（二级教授3人、三级教授6人），具有博士、硕士学位教师396人。专业课教师中，“双师型”教师占92%。享受国务院特殊津贴1人、新中国60年畜牧兽医科技杰出贡献人物1 人，江苏省科技创新团队2个、江苏省优秀教学团队1个、江苏省教学名师1人、江苏省有突出贡献的中青年专家1人、全国农业职教名师7人。 \\n    学院秉承“紧扣农牧产业链办学，紧密结合产学研育人，紧跟区域增长极发展”的办学理念，设有动物科技学院、动物医学院、动物药学院、食品科技学院、宠物科技学院、园林园艺系、水产科技系、农商管理系、农业物联网系、农业工程系和基础部、思政部、体育部等10个二级院系和3个部。围绕农牧产业链设置46个专业，其中，国家级重点专业6个、省级品牌专业3个、省级特色专业3个、省重点专业群建设点4个。 \\n    学院先后获省部级以上教育教学成果60多项，其中，国家级教学成果一等奖1项、二等奖1项，省级教学成果特等奖1项、一等奖2项、二等奖4项，国家级精品课程2门、国家级精品共享课程2门，省级精品课程6门，国家级宠物类专业教学资源库1个。 \\n    学院建有国家水禽基因库和姜曲海猪保种场2个国家级保种与研发机构和江苏省兽用生物制药高技术研究重点实验室、省动物医药创制中心、省畜产品深加工工程技术研究开发中心、省种猪生产性能测定站、省动物药品工程研究开发中心、省水禽繁育推广中心、省宠物（藏獒）繁育中心、省现代畜牧与新兽药工程技术中心等14个省级研发中心、12个市级研发平台、8个院级研究所。 \\n    学院升格以来，先后承担国家发改委重大项目1项、农业部项目28项、科技部项目5项、教育部项目6项、国家自然科学基金3项，部、省、市级项目共1000多项，培育出了具有自主知识产权的苏姜猪。开发国家级二类新兽药2个，填补了江苏省二类新兽药的空白。先后获农业部中华农业科技奖二等奖1项、三等奖1项，江苏省科技进步二等奖2项、三等奖3项，江苏省农业技术推广奖一等奖1项、二等奖2项、三等奖2项。 \\n    学院组建了科技服务团队，强力推进强农富民“五项工程”，实施“品种+技术+基地”服务模式，推广新品种、新技术、新成果，科技服务“三农”，有力地推动了当地经济的发展。 \\n    学院与美国、英国、德国、澳大利亚、荷兰、新加坡、台湾以及非洲、东南亚等国家和地区的60余所院校或教育培训机构建立了友好合作关系。多次举办非洲及东南亚籍动物科学技术专业留学生培训班，招收了全日制老挝、印尼留学生。 \\n    近十年来，学院获得了“全国职教先进单位”、“全国高校毕业生就业50强”、 “全国职业院校就业竞争力示范校”、“江苏省文明单位标兵”、“江苏省职业教育先进单位”、“江苏省高校毕业生就业先进单位”、“江苏省职业技能大赛先进高校”、“江苏省科技工作先进高校”、“江苏省师资队伍建设先进高校”、“江苏省大学生思想政治工作先进单位”、“江苏省大学生创业教育示范校”、“江苏省和谐校园”、“江苏省园林式单位”、 “江苏省民族团结进步先进集体”、“全国农业援藏工作先进单位”、“江苏省扶贫促小康先进集体”、“江苏省挂县强农富民工程先进单位”、“江苏省科技富民突出贡献单位”、“江苏省教育宣传工作先进单位”、 “江苏省后勤管理先进单位”、“江苏省高校文明食堂先进单位”等荣誉称号。 \\n    学院牢固树立“质量立校、特色兴校、人才强校、科研促校”的发展理念，以服务“三农”为办学宗旨，以学生就业为导向，走产学研结合之路，全面推进素质教育，努力培养现代农牧业所需要的高素质技术技能型人才，促进了学院全面协调可持续发展。 \\n    关山初度尘未洗，策马扬鞭再奋蹄。展望“十三五”发展，学院将进一步弘扬“团结拼搏、负重奋进、坚韧不拔、争创第一”的牧院精神，开拓进取、奋勇争先，为把学院建设成为“国内领先、世界知名”更高层次的农牧类高职院而努力奋斗！\",\"master\":\"暂无信息\",\"doctor\":\"暂无信息\",\"createtime\":25570,\"updatetime\":25570,\"refer_year\":null,\"wen_ben_rank\":0,\"wen_zhuan_rank\":209819,\"li_ben_rank\":0,\"li_zhuan_rank\":291596,\"wen_ben_score\":null,\"wen_zhuan_score\":280,\"li_ben_score\":null,\"li_zhuan_score\":275,\"creation_time\":\"暂无信息\",\"subjection\":\"江苏省教育厅\",\"disciplines\":\"暂无信息\",\"students\":\"1万5千人\",\"academician\":\"暂无信息\",\"weburl\":\"\",\"address\":\"江苏省泰州市凤凰东路8号\",\"telephone\":null,\"mailbox\":null,\"index_study\":\"5\",\"index_life\":\"5\",\"index_obtain\":\"5\",\"index_count\":\"5\",\"index_study_p\":100,\"index_life_p\":100,\"index_obtain_p\":100,\"index_count_p\":100,\"flags\":[],\"k1ist1\":[\"夏季高考\"],\"k1ist2\":[\"本科批次\",\"专科批次\"],\"k1ist3\":[\"文理\"],\"k1ist4\":[\"不限\",\"物理化学生物\",\"物理化学政治\",\"物理化学历史\",\"物理化学地理\",\"物理生物政治\",\"物理生物历史\",\"物理生物地理\",\"物理政治历史\",\"物理政治地理\",\"物理历史地理\",\"化学生物政治\",\"化学生物历史\",\"化学生物地理\",\"化学政治历史\",\"化学政治地理\",\"化学历史地理\",\"生物政治历史\",\"生物政治地理\",\"生物历史地理\",\"政治历史地理\"],\"k1ist5\":[\"2019\",\"2018\",\"2017\"],\"k1ist6\":[\"农林果蔬\",\"畜牧养殖\",\"电工电子\",\"护理\",\"医药\",\"土建\",\"信息技术\",\"汽车\",\"财经\",\"商贸\",\"学前教育\",\"机械\",\"机电一体化\",\"旅游服务\",\"化工\",\"文秘\",\"烹饪\",\"服装\"],\"k4_id\":0,\"schoolPro\":[{\"id\":162525,\"school_id\":769,\"subject\":\"文\\/理\",\"years\":2019,\"name\":\"学校专科最低分\",\"lowest\":\"351\\/306\",\"plan\":\"\",\"seating\":\"0\",\"create_time\":0,\"subjects\":\"|物理|化学|生物|政治|历史|地理|\",\"average\":\"328.5\",\"code\":\"0\",\"major\":\"暂无选科数据\",\"ranks\":\"无\",\"fraction\":\"328.5\",\"rank\":\"0\",\"position\":\"专科\",\"arts\":\"无\"}],\"schoolPlan\":[],\"schoolScore\":[{\"id\":29576,\"school_id\":769,\"years\":2019,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"文科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":45,\"ratio\":100,\"joins\":45,\"maximize\":\"479.00\",\"lowest\":\"351.00\",\"rank_min\":0,\"weigh\":0,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":0},{\"id\":30439,\"school_id\":769,\"years\":2019,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"理科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":35,\"ratio\":100,\"joins\":35,\"maximize\":\"438.00\",\"lowest\":\"350.00\",\"rank_min\":0,\"weigh\":0,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":0},{\"id\":24619,\"school_id\":769,\"years\":2018,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"理科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":30,\"ratio\":100,\"joins\":30,\"maximize\":\"424.00\",\"lowest\":\"275.00\",\"rank_min\":284024,\"weigh\":24619,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":1562639728},{\"id\":23679,\"school_id\":769,\"years\":2018,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"文科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":50,\"ratio\":100,\"joins\":50,\"maximize\":\"454.00\",\"lowest\":\"280.00\",\"rank_min\":197223,\"weigh\":23679,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":1562248028}],\"isCollect\":0,\"subject\":[]}}";
        return a;
    }


}

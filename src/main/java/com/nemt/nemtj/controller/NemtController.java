package com.nemt.nemtj.controller;

import com.alibaba.fastjson.JSON;
import com.nemt.nemtj.dao.AesMapper;
import com.nemt.nemtj.util.JedisPoolUtil;
import com.nemt.nemtj.wxbean.ApiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;


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


    @Autowired
    private AesMapper aesMapper;

    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis=jedisPool.getResource();





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




    @RequestMapping(path = "/index/index", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getIndex(@RequestBody String data) throws Exception {

        String res="{\"code\":0,\"msg\":\"\",\"time\":\"1594102892\",\"data\":{\"indexList\":[{\"id\":29,\"title\":\"准考证打印通知\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200623\\/7d2c3da323e284da362040ef2c6776dd.png\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/6fGfpsAXj-idQvidRkxeNg\"},{\"id\":28,\"title\":\"高职本科分段\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200613\\/4bffe16dd919463c54b8096d8d70356d.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/hELFkqDnXj6fj-wJl74MWQ\"},{\"id\":20,\"title\":\"农村专项计划\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200613\\/a881c9650c1bf14e5558655c84091ca8.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/97FKdLeacvdChegjql9Fqw\"},{\"id\":7,\"title\":\"20\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200504\\/6c425fa4285e8393b19e55bdc7ddeb51.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/bc1qsxN14L_7YLWiSXQRfQ\"}],\"serveList\":[{\"id\":5,\"title\":\"常见问题大全\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200425\\/d761223fe8e71333a38687023110ae64.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=https:\\/\\/mp.weixin.qq.com\\/s\\/bc1qsxN14L_7YLWiSXQRfQ\"},{\"id\":3,\"title\":\"春考计划\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200425\\/796b06f7938667e64d353dd747beb5a3.jpg\",\"url\":\"\\/pages\\/school\\/spring_search\\/main\"},{\"id\":4,\"title\":\"志愿填报\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200425\\/fd634a8deaa77716df81b953509dba3f.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?id=7\"}],\"articleList\":[{\"id\":25,\"title\":\"公安、消防、军校分数线看这！\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/k71KJo0dALJWeRwptCOh3g\",\"description\":\"1\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/default-img.jpg\"}],\"adNews\":[{\"id\":123,\"title\":\"20年高考作文\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/2PSgeQh8YQr0UzcbWY8eBg\",\"description\":\"全国合集\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200707\\/2af2a60afaa0b22f1ed6db567d47e5b5.jpg\"}],\"menuList\":[{\"id\":10,\"title\":\"查大学\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/632ea621cdbc876ace8633b657314824.jpg\",\"url\":\"\\/pages\\/school\\/search\\/main\"},{\"id\":12,\"title\":\"招生计划\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/e177bcf0f001d18dc63f8efd69f48421.jpg\",\"url\":\"\\/pages\\/major\\/plan\\/main\"},{\"id\":11,\"title\":\"查专业\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/7c2b4cef45c0280e448028adf038b3ae.jpg\",\"url\":\"\\/pages\\/major\\/main\"},{\"id\":13,\"title\":\"一分一段\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200505\\/58b5287d3c6efc3c99dad62a8d072dcc.jpg\",\"url\":\"\\/pages\\/inform\\/news\\/main\"},{\"id\":24,\"title\":\"单招\\/综评\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200526\\/f8cc55c56a27315c70c9f93683d6fb37.jpg\",\"url\":\"\\/pages\\/major\\/plan\\/main3\"},{\"id\":36,\"title\":\"计算器\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/9f5215034bf5ba2698538c3a3bd8ae8e.jpg\",\"url\":\"\\/pages\\/mine\\/tool\\/main\"},{\"id\":35,\"title\":\"艺体类\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/4a57a7aa34c8cbe49ea22c07ba94887a.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?id=120\"},{\"id\":34,\"title\":\"学科评估\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/cbbf959a0319a385892ed5c93eb17599.jpg\",\"url\":\"\\/pages\\/major\\/subject\\/main\"},{\"id\":33,\"title\":\"留学\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/377c56530a494f78bf399e0123fba784.jpg\",\"url\":\"\\/pages\\/html\\/article\\/main?link=http:\\/\\/ks.sdmengdao.com\\/web\\/liuxue\\/\"},{\"id\":32,\"title\":\"高考估分\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200630\\/ca9ce45c5384929e5cd62393c72ff914.jpg\",\"url\":\"\\/pages\\/question\\/main\"}],\"policyList\":[{\"id\":125,\"title\":\"53万人参加高考\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Ney5MGXvRincNEf-0haNPA\",\"description\":\"不准提前交卷\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200705\\/e9371a01232c11d7f7fca536cb7ca0c2.jpg\"},{\"id\":117,\"title\":\"准考证打印通知\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/6fGfpsAXj-idQvidRkxeNg\",\"description\":\"准考证\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200623\\/7d2c3da323e284da362040ef2c6776dd.png\"},{\"id\":113,\"title\":\"10所高校2020在山东省综合评价招生简章汇总（全）\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/UDqm0yEp0TD3ZiLEWNQWww\",\"description\":\"招生简章\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200615\\/f21f419f2057798ddaeafa48aae4d00b.jpg\"},{\"id\":108,\"title\":\"高考缴费+等级考选报，夏季高考生要注意了，常见问题和流程图你看了吗？\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Z3NeyXxVKCxZEKEhvrQuCQ\",\"description\":\"缴费选报\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200524\\/7f7766c35dd7dcc24c77c9757260cb16.png\"},{\"id\":79,\"title\":\"全面开学！山东各学段复学复课时间确定！\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Ydrxk_y-oV1AiHwqh7dvyQ\",\"description\":\"开学时间\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200514\\/6900b4de8c135c40a556edd25fda7a77.png\"},{\"id\":77,\"title\":\"高考6选3时间定了！还不需要交钱!\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/Jf20E_a_6NbTeBRq_DDWbw\",\"description\":\"选科通知\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200512\\/1e504421fe795807d6377e613d203bac.jpg\"},{\"id\":75,\"title\":\"高考缴费通知，只缴语数外，附带缴费流程图！\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/r1WI1g6WCZWWr-7L-2-BfQ\",\"description\":\"缴费通知\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200512\\/fd3604986d5971d282bc260db96bed31.jpg\"},{\"id\":71,\"title\":\"单招\\/综合评价报名时间已公布，附带招生计划表\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/T0g3Wv32WB1hP0Py2gnJvw\",\"description\":\"信息\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200506\\/1b173aeed807c26f6fee7556ea77b59d.jpg\"},{\"id\":60,\"title\":\"高考补报名通知\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/6Rjzh10lCX2cL7Vo7yt1Ng\",\"description\":\"报名\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200429\\/fb4cb7d65149b039770df0906f3c418c.png\"},{\"id\":59,\"title\":\"单招要进行网络考试了！山东春考、体考、艺考、单招等考试时间公布\",\"wechat_link\":\"http:\\/\\/ks.sdmengdao.com\\/web\\/liuxue\\/\",\"description\":\"政策\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200427\\/8938b70ec50b449d5ab140ea5b923445.png\"},{\"id\":52,\"title\":\"关注！山东高三开学后，走读生疫情防控如何做？\",\"wechat_link\":\"http:\\/\\/ks.sdmengdao.com\\/web\\/liuxue\\/\",\"description\":\"开学\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200414\\/2ba2321bf81a51237c2c04c9ee1b5037.png\"}],\"infoList\":[{\"id\":126,\"title\":\"山东十六地市考场安排\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/KY5lPVpdHlEVLG4DFv74eQ\",\"description\":\"仅供参考\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200704\\/a0a6bba8a7484e356ef2304efec5e23d.png\"},{\"id\":116,\"title\":\"一模二模划线汇总分析\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/eIn6VrarklFCfx1oePB5HQ\",\"description\":\"持续更新\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200616\\/9e50be4acdde97bf4e39104aebc32df5.jpg\"},{\"id\":106,\"title\":\"9科目全，答案全，可下载，潍坊二模试题+答案\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/k3YC3Fka5uX_WS1yEQGuYQ\",\"description\":\"试题\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200524\\/dab0ba4dd819742d1d02f2cf36910b54.png\"},{\"id\":105,\"title\":\"9科全！答案全！可下载！济南二模试题+答案\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/2jrM0xO1dMbx50oF2yQH0w\",\"description\":\"试题\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200524\\/8f6c64c94e346ce0bea0647818dd9c33.png\"},{\"id\":102,\"title\":\"学业水平考试通知\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/NYz5HXICeElR1xySCms_WQ\",\"description\":\"考试\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200514\\/6900b4de8c135c40a556edd25fda7a77.png\"},{\"id\":62,\"title\":\"已更新试题及获取方式\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/3af-7hraQfRUVuyh-A9w9w\",\"description\":\"考试信息\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200504\\/e9bc14d96a63848235a42165e22fab13.png\"},{\"id\":80,\"title\":\"济宁二模考试，试题+答案\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s?__biz=Mzg2NDE5NDIzNA==&mid=2247489873&idx=3&sn=4459c294e2818adaac92ae33a9f5b990&chksm=ce6c4284f91bcb92c5933e9a39b7dd2e983acb043cf12e0baf49f73cd88a5b6e63f1f947d0c7&token=2118490023&lang=zh_CN#rd\",\"description\":\"试题\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200514\\/662e0b126bf6e2d16178ffee534ba796.png\"},{\"id\":72,\"title\":\"高考电脑阅卷流程是这样的！认真读完说不定多拿20分\",\"wechat_link\":\"https:\\/\\/mp.weixin.qq.com\\/s\\/49j8Sa2qzFrqinddL4WCVg\",\"description\":\"小技巧\",\"image\":\"https:\\/\\/image.tianziyuan.cn\\/uploads\\/20200506\\/96df0e0e8ea84e0d5e72be50158260fc.png\"}],\"subject\":[],\"free\":0,\"consume\":2,\"title\":\"高考加油\",\"title2\":\"必胜！\",\"days\":\"000\",\"section\":\"40\",\"rank\":\"50000\",\"distance\":\"距离高考\"}}";

        return res;

    }




}

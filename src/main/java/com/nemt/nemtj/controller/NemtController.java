package com.nemt.nemtj.controller;

import com.alibaba.fastjson.JSON;
import com.nemt.nemtj.dao.AesMapper;
import com.nemt.nemtj.util.JedisPoolUtil;
import com.nemt.nemtj.wxbean.ApiToken;
import com.nemt.nemtj.wxbean.LoginInfo;
import com.nemt.nemtj.wxbean.User;
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


    @RequestMapping(path = "/school/getSchoolInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSchoolInfo(@RequestBody String data) throws Exception {
        String a="{\"code\":0,\"msg\":\"\",\"time\":\"1594818351\",\"data\":{\"id\":769,\"name\":\"江苏农牧科技职业学院\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/C806.jpg\",\"number\":\"C806\",\"type\":\"农业\",\"public\":null,\"position\":\"专科\",\"ranking\":0,\"district\":\"江苏\",\"flag\":\"\",\"man\":51,\"page\":\"6月26日更新\",\"page_wen\":\"6月26日更新\",\"info\":\"江苏农牧科技职业学院始建于1958年9月，其前身是“泰县农业中等技术学校”。1961年9月，更名为“江苏省泰州畜牧兽医学校”；1995年4月，更名为“江苏省畜牧兽医学校”；2001年6月，独立升格为“江苏畜牧兽医职业技术学院”；2013年3月，更名为“江苏农牧科技职业学院”。 \\n学院是我国东南沿海地区乃至我国南方14个省（市、自治区）唯一以培养农牧科技类技术技能型人才为主的高等院校，座落在京剧大师 梅兰芳的故乡——江苏省泰州市。 \\n2005年，学院以“优秀”等级在全省率先通过教育部高职高专人才培养水平评估；2008年，被确定为“江苏省示范性高等职业院校”；2010年，被教育部、财政部确定为“国家示范性高等职业院校建设计划”首批“国家骨干高职院建设单位”；2013年，以“优秀”等级通过骨干高职院校建设项目验收。从此，学院进入全国高职院校第一方阵，成为全国农业高职院校的排头兵。 \\n    学院占地面积4550余亩，校舍建筑面积55万平方米，形成了以凤凰路校区为主体、以江苏现代畜牧科技园和江苏中药科技园为两翼的“一主两翼”办学格局。学院拥有各级各类实验室、实训室200多个，建有江苏现代畜牧科技园、江苏中药科技园、江苏苏姜种猪有限公司、江苏倍康药业有限公司、教学动物医院、泰爱牧宠物医院等36个院内实训基地，校外实训实习基地700多个，图书馆藏书102万册，教学科研仪器设备总值1.54亿元。 \\n    学院面向全国25个省（市、自治区）招生，现有在校生近15000人。中国科学院院士、南开大学原校长饶子和担任学院名誉院长，国内外20多位知名教授包括6位两院院士担任学院兼职教授。学院现有教职工1007人，其中，副高以上职称教师202人，正教授52人（二级教授3人、三级教授6人），具有博士、硕士学位教师396人。专业课教师中，“双师型”教师占92%。享受国务院特殊津贴1人、新中国60年畜牧兽医科技杰出贡献人物1 人，江苏省科技创新团队2个、江苏省优秀教学团队1个、江苏省教学名师1人、江苏省有突出贡献的中青年专家1人、全国农业职教名师7人。 \\n    学院秉承“紧扣农牧产业链办学，紧密结合产学研育人，紧跟区域增长极发展”的办学理念，设有动物科技学院、动物医学院、动物药学院、食品科技学院、宠物科技学院、园林园艺系、水产科技系、农商管理系、农业物联网系、农业工程系和基础部、思政部、体育部等10个二级院系和3个部。围绕农牧产业链设置46个专业，其中，国家级重点专业6个、省级品牌专业3个、省级特色专业3个、省重点专业群建设点4个。 \\n    学院先后获省部级以上教育教学成果60多项，其中，国家级教学成果一等奖1项、二等奖1项，省级教学成果特等奖1项、一等奖2项、二等奖4项，国家级精品课程2门、国家级精品共享课程2门，省级精品课程6门，国家级宠物类专业教学资源库1个。 \\n    学院建有国家水禽基因库和姜曲海猪保种场2个国家级保种与研发机构和江苏省兽用生物制药高技术研究重点实验室、省动物医药创制中心、省畜产品深加工工程技术研究开发中心、省种猪生产性能测定站、省动物药品工程研究开发中心、省水禽繁育推广中心、省宠物（藏獒）繁育中心、省现代畜牧与新兽药工程技术中心等14个省级研发中心、12个市级研发平台、8个院级研究所。 \\n    学院升格以来，先后承担国家发改委重大项目1项、农业部项目28项、科技部项目5项、教育部项目6项、国家自然科学基金3项，部、省、市级项目共1000多项，培育出了具有自主知识产权的苏姜猪。开发国家级二类新兽药2个，填补了江苏省二类新兽药的空白。先后获农业部中华农业科技奖二等奖1项、三等奖1项，江苏省科技进步二等奖2项、三等奖3项，江苏省农业技术推广奖一等奖1项、二等奖2项、三等奖2项。 \\n    学院组建了科技服务团队，强力推进强农富民“五项工程”，实施“品种+技术+基地”服务模式，推广新品种、新技术、新成果，科技服务“三农”，有力地推动了当地经济的发展。 \\n    学院与美国、英国、德国、澳大利亚、荷兰、新加坡、台湾以及非洲、东南亚等国家和地区的60余所院校或教育培训机构建立了友好合作关系。多次举办非洲及东南亚籍动物科学技术专业留学生培训班，招收了全日制老挝、印尼留学生。 \\n    近十年来，学院获得了“全国职教先进单位”、“全国高校毕业生就业50强”、 “全国职业院校就业竞争力示范校”、“江苏省文明单位标兵”、“江苏省职业教育先进单位”、“江苏省高校毕业生就业先进单位”、“江苏省职业技能大赛先进高校”、“江苏省科技工作先进高校”、“江苏省师资队伍建设先进高校”、“江苏省大学生思想政治工作先进单位”、“江苏省大学生创业教育示范校”、“江苏省和谐校园”、“江苏省园林式单位”、 “江苏省民族团结进步先进集体”、“全国农业援藏工作先进单位”、“江苏省扶贫促小康先进集体”、“江苏省挂县强农富民工程先进单位”、“江苏省科技富民突出贡献单位”、“江苏省教育宣传工作先进单位”、 “江苏省后勤管理先进单位”、“江苏省高校文明食堂先进单位”等荣誉称号。 \\n    学院牢固树立“质量立校、特色兴校、人才强校、科研促校”的发展理念，以服务“三农”为办学宗旨，以学生就业为导向，走产学研结合之路，全面推进素质教育，努力培养现代农牧业所需要的高素质技术技能型人才，促进了学院全面协调可持续发展。 \\n    关山初度尘未洗，策马扬鞭再奋蹄。展望“十三五”发展，学院将进一步弘扬“团结拼搏、负重奋进、坚韧不拔、争创第一”的牧院精神，开拓进取、奋勇争先，为把学院建设成为“国内领先、世界知名”更高层次的农牧类高职院而努力奋斗！\",\"master\":\"暂无信息\",\"doctor\":\"暂无信息\",\"createtime\":25570,\"updatetime\":25570,\"refer_year\":null,\"wen_ben_rank\":0,\"wen_zhuan_rank\":209819,\"li_ben_rank\":0,\"li_zhuan_rank\":291596,\"wen_ben_score\":null,\"wen_zhuan_score\":280,\"li_ben_score\":null,\"li_zhuan_score\":275,\"creation_time\":\"暂无信息\",\"subjection\":\"江苏省教育厅\",\"disciplines\":\"暂无信息\",\"students\":\"1万5千人\",\"academician\":\"暂无信息\",\"weburl\":\"\",\"address\":\"江苏省泰州市凤凰东路8号\",\"telephone\":null,\"mailbox\":null,\"index_study\":\"5\",\"index_life\":\"5\",\"index_obtain\":\"5\",\"index_count\":\"5\",\"index_study_p\":100,\"index_life_p\":100,\"index_obtain_p\":100,\"index_count_p\":100,\"flags\":[],\"k1ist1\":[\"夏季高考\"],\"k1ist2\":[\"本科批次\",\"专科批次\"],\"k1ist3\":[\"文理\"],\"k1ist4\":[\"不限\",\"物理化学生物\",\"物理化学政治\",\"物理化学历史\",\"物理化学地理\",\"物理生物政治\",\"物理生物历史\",\"物理生物地理\",\"物理政治历史\",\"物理政治地理\",\"物理历史地理\",\"化学生物政治\",\"化学生物历史\",\"化学生物地理\",\"化学政治历史\",\"化学政治地理\",\"化学历史地理\",\"生物政治历史\",\"生物政治地理\",\"生物历史地理\",\"政治历史地理\"],\"k1ist5\":[\"2019\",\"2018\",\"2017\"],\"k1ist6\":[\"农林果蔬\",\"畜牧养殖\",\"电工电子\",\"护理\",\"医药\",\"土建\",\"信息技术\",\"汽车\",\"财经\",\"商贸\",\"学前教育\",\"机械\",\"机电一体化\",\"旅游服务\",\"化工\",\"文秘\",\"烹饪\",\"服装\"],\"k4_id\":0,\"schoolPro\":[{\"id\":162525,\"school_id\":769,\"subject\":\"文\\/理\",\"years\":2019,\"name\":\"学校专科最低分\",\"lowest\":\"351\\/306\",\"plan\":\"\",\"seating\":\"0\",\"create_time\":0,\"subjects\":\"|物理|化学|生物|政治|历史|地理|\",\"average\":\"328.5\",\"code\":\"0\",\"major\":\"暂无选科数据\",\"ranks\":\"无\",\"fraction\":\"328.5\",\"rank\":\"0\",\"position\":\"专科\",\"arts\":\"无\"}],\"schoolPlan\":[],\"schoolScore\":[{\"id\":29576,\"school_id\":769,\"years\":2019,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"文科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":45,\"ratio\":100,\"joins\":45,\"maximize\":\"479.00\",\"lowest\":\"351.00\",\"rank_min\":0,\"weigh\":0,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":0},{\"id\":30439,\"school_id\":769,\"years\":2019,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"理科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":35,\"ratio\":100,\"joins\":35,\"maximize\":\"438.00\",\"lowest\":\"350.00\",\"rank_min\":0,\"weigh\":0,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":0},{\"id\":24619,\"school_id\":769,\"years\":2018,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"理科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":30,\"ratio\":100,\"joins\":30,\"maximize\":\"424.00\",\"lowest\":\"275.00\",\"rank_min\":284024,\"weigh\":24619,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":1562639728},{\"id\":23679,\"school_id\":769,\"years\":2018,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"文科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":50,\"ratio\":100,\"joins\":50,\"maximize\":\"454.00\",\"lowest\":\"280.00\",\"rank_min\":197223,\"weigh\":23679,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":1562248028}],\"isCollect\":0,\"subject\":[]}}";
        return a;
    }



}

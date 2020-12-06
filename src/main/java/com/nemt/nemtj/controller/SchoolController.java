package com.nemt.nemtj.controller;

import com.alibaba.fastjson.JSON;
import com.nemt.nemtj.dao.AesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private AesMapper aesMapper;

    @RequestMapping(path = "/school/getRecList", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRecList(@RequestBody String data) throws Exception {
        //List<Map> maps=aesMapper.getRecList();
        Map map=(Map) JSON.parse(data);
        int  val=Integer.parseInt(map.get("val").toString());
        int  section= 10;
        if(map.get("section")!=null){
            section=Integer.parseInt(map.get("section").toString());
        }
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



    @RequestMapping(path = "/school/getSchoolInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSchoolInfo(@RequestBody String data) throws Exception {
        String a="{\"code\":0,\"msg\":\"\",\"time\":\"1594818351\",\"data\":{\"id\":769,\"name\":\"江苏农牧科技职业学院\",\"avatar\":\"https:\\/\\/image.tianziyuan.cn\\/logo\\/C806.jpg\",\"number\":\"C806\",\"type\":\"农业\",\"public\":null,\"position\":\"专科\",\"ranking\":0,\"district\":\"江苏\",\"flag\":\"\",\"man\":51,\"page\":\"6月26日更新\",\"page_wen\":\"6月26日更新\",\"info\":\"江苏农牧科技职业学院始建于1958年9月，其前身是“泰县农业中等技术学校”。1961年9月，更名为“江苏省泰州畜牧兽医学校”；1995年4月，更名为“江苏省畜牧兽医学校”；2001年6月，独立升格为“江苏畜牧兽医职业技术学院”；2013年3月，更名为“江苏农牧科技职业学院”。 \\n学院是我国东南沿海地区乃至我国南方14个省（市、自治区）唯一以培养农牧科技类技术技能型人才为主的高等院校，座落在京剧大师 梅兰芳的故乡——江苏省泰州市。 \\n2005年，学院以“优秀”等级在全省率先通过教育部高职高专人才培养水平评估；2008年，被确定为“江苏省示范性高等职业院校”；2010年，被教育部、财政部确定为“国家示范性高等职业院校建设计划”首批“国家骨干高职院建设单位”；2013年，以“优秀”等级通过骨干高职院校建设项目验收。从此，学院进入全国高职院校第一方阵，成为全国农业高职院校的排头兵。 \\n    学院占地面积4550余亩，校舍建筑面积55万平方米，形成了以凤凰路校区为主体、以江苏现代畜牧科技园和江苏中药科技园为两翼的“一主两翼”办学格局。学院拥有各级各类实验室、实训室200多个，建有江苏现代畜牧科技园、江苏中药科技园、江苏苏姜种猪有限公司、江苏倍康药业有限公司、教学动物医院、泰爱牧宠物医院等36个院内实训基地，校外实训实习基地700多个，图书馆藏书102万册，教学科研仪器设备总值1.54亿元。 \\n    学院面向全国25个省（市、自治区）招生，现有在校生近15000人。中国科学院院士、南开大学原校长饶子和担任学院名誉院长，国内外20多位知名教授包括6位两院院士担任学院兼职教授。学院现有教职工1007人，其中，副高以上职称教师202人，正教授52人（二级教授3人、三级教授6人），具有博士、硕士学位教师396人。专业课教师中，“双师型”教师占92%。享受国务院特殊津贴1人、新中国60年畜牧兽医科技杰出贡献人物1 人，江苏省科技创新团队2个、江苏省优秀教学团队1个、江苏省教学名师1人、江苏省有突出贡献的中青年专家1人、全国农业职教名师7人。 \\n    学院秉承“紧扣农牧产业链办学，紧密结合产学研育人，紧跟区域增长极发展”的办学理念，设有动物科技学院、动物医学院、动物药学院、食品科技学院、宠物科技学院、园林园艺系、水产科技系、农商管理系、农业物联网系、农业工程系和基础部、思政部、体育部等10个二级院系和3个部。围绕农牧产业链设置46个专业，其中，国家级重点专业6个、省级品牌专业3个、省级特色专业3个、省重点专业群建设点4个。 \\n    学院先后获省部级以上教育教学成果60多项，其中，国家级教学成果一等奖1项、二等奖1项，省级教学成果特等奖1项、一等奖2项、二等奖4项，国家级精品课程2门、国家级精品共享课程2门，省级精品课程6门，国家级宠物类专业教学资源库1个。 \\n    学院建有国家水禽基因库和姜曲海猪保种场2个国家级保种与研发机构和江苏省兽用生物制药高技术研究重点实验室、省动物医药创制中心、省畜产品深加工工程技术研究开发中心、省种猪生产性能测定站、省动物药品工程研究开发中心、省水禽繁育推广中心、省宠物（藏獒）繁育中心、省现代畜牧与新兽药工程技术中心等14个省级研发中心、12个市级研发平台、8个院级研究所。 \\n    学院升格以来，先后承担国家发改委重大项目1项、农业部项目28项、科技部项目5项、教育部项目6项、国家自然科学基金3项，部、省、市级项目共1000多项，培育出了具有自主知识产权的苏姜猪。开发国家级二类新兽药2个，填补了江苏省二类新兽药的空白。先后获农业部中华农业科技奖二等奖1项、三等奖1项，江苏省科技进步二等奖2项、三等奖3项，江苏省农业技术推广奖一等奖1项、二等奖2项、三等奖2项。 \\n    学院组建了科技服务团队，强力推进强农富民“五项工程”，实施“品种+技术+基地”服务模式，推广新品种、新技术、新成果，科技服务“三农”，有力地推动了当地经济的发展。 \\n    学院与美国、英国、德国、澳大利亚、荷兰、新加坡、台湾以及非洲、东南亚等国家和地区的60余所院校或教育培训机构建立了友好合作关系。多次举办非洲及东南亚籍动物科学技术专业留学生培训班，招收了全日制老挝、印尼留学生。 \\n    近十年来，学院获得了“全国职教先进单位”、“全国高校毕业生就业50强”、 “全国职业院校就业竞争力示范校”、“江苏省文明单位标兵”、“江苏省职业教育先进单位”、“江苏省高校毕业生就业先进单位”、“江苏省职业技能大赛先进高校”、“江苏省科技工作先进高校”、“江苏省师资队伍建设先进高校”、“江苏省大学生思想政治工作先进单位”、“江苏省大学生创业教育示范校”、“江苏省和谐校园”、“江苏省园林式单位”、 “江苏省民族团结进步先进集体”、“全国农业援藏工作先进单位”、“江苏省扶贫促小康先进集体”、“江苏省挂县强农富民工程先进单位”、“江苏省科技富民突出贡献单位”、“江苏省教育宣传工作先进单位”、 “江苏省后勤管理先进单位”、“江苏省高校文明食堂先进单位”等荣誉称号。 \\n    学院牢固树立“质量立校、特色兴校、人才强校、科研促校”的发展理念，以服务“三农”为办学宗旨，以学生就业为导向，走产学研结合之路，全面推进素质教育，努力培养现代农牧业所需要的高素质技术技能型人才，促进了学院全面协调可持续发展。 \\n    关山初度尘未洗，策马扬鞭再奋蹄。展望“十三五”发展，学院将进一步弘扬“团结拼搏、负重奋进、坚韧不拔、争创第一”的牧院精神，开拓进取、奋勇争先，为把学院建设成为“国内领先、世界知名”更高层次的农牧类高职院而努力奋斗！\",\"master\":\"暂无信息\",\"doctor\":\"暂无信息\",\"createtime\":25570,\"updatetime\":25570,\"refer_year\":null,\"wen_ben_rank\":0,\"wen_zhuan_rank\":209819,\"li_ben_rank\":0,\"li_zhuan_rank\":291596,\"wen_ben_score\":null,\"wen_zhuan_score\":280,\"li_ben_score\":null,\"li_zhuan_score\":275,\"creation_time\":\"暂无信息\",\"subjection\":\"江苏省教育厅\",\"disciplines\":\"暂无信息\",\"students\":\"1万5千人\",\"academician\":\"暂无信息\",\"weburl\":\"\",\"address\":\"江苏省泰州市凤凰东路8号\",\"telephone\":null,\"mailbox\":null,\"index_study\":\"5\",\"index_life\":\"5\",\"index_obtain\":\"5\",\"index_count\":\"5\",\"index_study_p\":100,\"index_life_p\":100,\"index_obtain_p\":100,\"index_count_p\":100,\"flags\":[],\"k1ist1\":[\"夏季高考\"],\"k1ist2\":[\"本科批次\",\"专科批次\"],\"k1ist3\":[\"文理\"],\"k1ist4\":[\"不限\",\"物理化学生物\",\"物理化学政治\",\"物理化学历史\",\"物理化学地理\",\"物理生物政治\",\"物理生物历史\",\"物理生物地理\",\"物理政治历史\",\"物理政治地理\",\"物理历史地理\",\"化学生物政治\",\"化学生物历史\",\"化学生物地理\",\"化学政治历史\",\"化学政治地理\",\"化学历史地理\",\"生物政治历史\",\"生物政治地理\",\"生物历史地理\",\"政治历史地理\"],\"k1ist5\":[\"2019\",\"2018\",\"2017\"],\"k1ist6\":[\"农林果蔬\",\"畜牧养殖\",\"电工电子\",\"护理\",\"医药\",\"土建\",\"信息技术\",\"汽车\",\"财经\",\"商贸\",\"学前教育\",\"机械\",\"机电一体化\",\"旅游服务\",\"化工\",\"文秘\",\"烹饪\",\"服装\"],\"k4_id\":0,\"schoolPro\":[{\"id\":162525,\"school_id\":769,\"subject\":\"文\\/理\",\"years\":2019,\"name\":\"学校专科最低分\",\"lowest\":\"351\\/306\",\"plan\":\"\",\"seating\":\"0\",\"create_time\":0,\"subjects\":\"|物理|化学|生物|政治|历史|地理|\",\"average\":\"328.5\",\"code\":\"0\",\"major\":\"暂无选科数据\",\"ranks\":\"无\",\"fraction\":\"328.5\",\"rank\":\"0\",\"position\":\"专科\",\"arts\":\"无\"}],\"schoolPlan\":[],\"schoolScore\":[{\"id\":29576,\"school_id\":769,\"years\":2019,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"文科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":45,\"ratio\":100,\"joins\":45,\"maximize\":\"479.00\",\"lowest\":\"351.00\",\"rank_min\":0,\"weigh\":0,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":0},{\"id\":30439,\"school_id\":769,\"years\":2019,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"理科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":35,\"ratio\":100,\"joins\":35,\"maximize\":\"438.00\",\"lowest\":\"350.00\",\"rank_min\":0,\"weigh\":0,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":0},{\"id\":24619,\"school_id\":769,\"years\":2018,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"理科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":30,\"ratio\":100,\"joins\":30,\"maximize\":\"424.00\",\"lowest\":\"275.00\",\"rank_min\":284024,\"weigh\":24619,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":1562639728},{\"id\":23679,\"school_id\":769,\"years\":2018,\"batch\":\"专科批次\",\"voluntary\":\"投档志愿\",\"subject\":\"文科\",\"position\":\"专科\",\"season\":\"秋招\",\"plan\":50,\"ratio\":100,\"joins\":50,\"maximize\":\"454.00\",\"lowest\":\"280.00\",\"rank_min\":197223,\"weigh\":23679,\"remark\":\"\",\"state\":\"1\",\"createtime\":0,\"updatetime\":1562248028}],\"isCollect\":0,\"subject\":[]}}";
        return a;
    }

}

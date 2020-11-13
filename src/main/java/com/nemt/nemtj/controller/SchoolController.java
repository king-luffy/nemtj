package com.nemt.nemtj.controller;

import com.alibaba.fastjson.JSON;
import com.nemt.nemtj.dao.AesMapper;
import com.nemt.nemtj.util.JedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

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

}

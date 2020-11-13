package com.nemt.nemtj.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaperController {

    @RequestMapping(path = "/test_paper/getTestPaper", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getTestPaper(@RequestBody String data) throws Exception {



        return "{\"code\":0,\"msg\":\"\",\"time\":\"1605249472\",\"data\":[{\"id\":1,\"name\":\"MBTI职业性格测试\",\"createtime\":1589942563},{\"id\":2,\"name\":\"霍兰德\",\"createtime\":1589942582}]}";
    }

    @RequestMapping(path = "/test_paper/getTestResult", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getTestResult(@RequestBody String data) throws Exception {



        return "{\"code\":0,\"msg\":\"\",\"time\":\"1605249473\",\"data\":[]}";
    }
}

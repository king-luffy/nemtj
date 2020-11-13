package com.nemt.nemtj.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CollectionController {

    @RequestMapping(path = "/collection/getCollectionList", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getTestResult(@RequestBody String data) throws Exception {



        return "{\"code\":0,\"msg\":\"\",\"time\":\"1605249473\",\"data\":[]}";
    }
}

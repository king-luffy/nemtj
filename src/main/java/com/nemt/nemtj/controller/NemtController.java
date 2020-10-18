package com.nemt.nemtj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

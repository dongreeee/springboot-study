package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class BasicController {
    @GetMapping("/")
    String hello(){
        return "index.html";
    }

    @GetMapping("/now")
    @ResponseBody
//    문자데이터 그대로 보내주세요
    String  now(){
        Date date = new Date();
        String nowTime = date.toString();
        System.out.println(nowTime);
        return nowTime;
    }
//    API : 프로그램의 사용법


}

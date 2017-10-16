package com.tao.springmvc_demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author TAO
 * @Date 2017/9/10 22:22
 */
@Controller
public class HelloController {

    @RequestMapping("index")
    public String hello(){
        System.out.println("hello world");
        return "index";
    }

    @RequestMapping("index2")
    public String hello2(){
        System.out.println("name:"+Thread.currentThread().getName());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "index";
    }
}

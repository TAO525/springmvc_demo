package com.tao.springmvc_demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 服务端推送技术
 * @Author TAO
 * @Date 2017/9/21 22:11
 */
@Controller
public class SseController {

    @RequestMapping(value = "/push",produces = "text/event-stream")
    @ResponseBody
    public String Random(){
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:NUMBER-"+random.nextInt()+"\n\n";//data 一定要加 属于数据格式
    }
}

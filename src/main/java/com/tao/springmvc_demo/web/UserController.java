package com.tao.springmvc_demo.web;

import com.alibaba.fastjson.JSON;
import com.tao.springmvc_demo.po.UserT;
import com.tao.springmvc_demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author TAO
 * @Date 2017/10/17 10:48
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user")
    @ResponseBody
    public String Random(){
        UserT userById = userService.getUserById(1);
        return "用户数据："+ JSON.toJSONString(userById);//data 一定要加 属于数据格式
    }

}

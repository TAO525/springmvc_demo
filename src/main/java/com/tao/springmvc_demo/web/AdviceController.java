package com.tao.springmvc_demo.web;

import com.tao.springmvc_demo.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author TAO
 * @Date 2017/9/15 1:08
 */
@Controller
public class AdviceController {

    @RequestMapping("advice")
    public String getSomething(@ModelAttribute("msg")String msg, DemoObj obj){
        throw  new IllegalArgumentException("非常抱歉，参数有误/"+"来自@ModelAttribute："+msg);

    }
}

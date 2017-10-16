package com.tao.springmvc_demo.web;

import com.tao.springmvc_demo.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 注解演示控制器
 * @Author TAO
 * @Date 2017/9/10 23:00
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody String index(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access";
    }

    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str, //3
                                            HttpServletRequest request) {
        System.out.println(request.getParameter("str"));// 本来就取不到的
        return "url:" + request.getRequestURL() + " can access,str: " + str;
    }

    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id,
                                                 HttpServletRequest request) {
        System.out.println(request.getParameter("id"));
        return "url:" + request.getRequestURL() + " can access,id: " + id;

    }

    @RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String passObj(DemoObj obj, HttpServletRequest request, Model model) {
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("name"));
        System.out.println(model);
        return "url:" + request.getRequestURL()
                + " can access, obj id: " + obj.getId()+" obj name:" + obj.getName();

    }

    @RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(HttpServletRequest request) {

        return "url:" + request.getRequestURL() + " can access";
    }

    @RequestMapping(value = "/obj2", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String passObj2(@RequestBody DemoObj obj, HttpServletRequest request, Model model) {
        return "url:" + request.getRequestURL()
                + " can access, obj id: " + obj.getId()+" obj name:" + obj.getName();

    }


}

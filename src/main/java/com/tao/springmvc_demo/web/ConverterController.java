package com.tao.springmvc_demo.web;

import com.tao.springmvc_demo.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author TAO
 * @Date 2017/9/21 21:45
 */
@Controller
public class ConverterController {

    @RequestMapping(value = "/convert", produces = "application/x-tao")
    @ResponseBody
    public DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }
}

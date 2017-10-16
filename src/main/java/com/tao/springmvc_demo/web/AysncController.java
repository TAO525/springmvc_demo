package com.tao.springmvc_demo.web;

import com.tao.springmvc_demo.domain.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author TAO
 * @Date 2017/9/21 23:43
 */
@Controller
public class AysncController {
    @Autowired
    PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredResult(){
        return pushService.getDeferredResult();
    }

}

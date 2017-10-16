package com.tao.springmvc_demo.domain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author TAO
 * @Date 2017/9/21 23:43
 */
@Service
public class PushService {
    private DeferredResult<String> deferredResult;

    public DeferredResult<String> getDeferredResult(){
        deferredResult = new DeferredResult<>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh2(){
//        System.out.println("方法进入");
        if(deferredResult != null){
            System.out.println("方法进入判断");
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
            System.out.println("方法退出");
        }
    }
}

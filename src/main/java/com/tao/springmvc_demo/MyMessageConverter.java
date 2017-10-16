package com.tao.springmvc_demo;

import com.tao.springmvc_demo.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author TAO
 * @Date 2017/9/21 21:35
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj>{

    public MyMessageConverter() {
        super(new MediaType("application","x-tao", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String s = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] split = s.split("-");
        return new DemoObj(Long.valueOf(split[0]),split[1]);
    }

    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String s = "hello:" + obj.getId() + "-" + obj.getName();
        httpOutputMessage.getBody().write(s.getBytes());
    }
}

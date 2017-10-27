package com.tao.springmvc_demo.common;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * @Author TAO
 * @Date 2017/10/27 11:21
 */
public class PaginationPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return false;
    }
}

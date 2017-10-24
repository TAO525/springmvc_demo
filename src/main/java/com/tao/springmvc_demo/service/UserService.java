package com.tao.springmvc_demo.service;

import ch.qos.logback.core.joran.spi.ActionException;
import com.tao.springmvc_demo.po.UserT;

/**
 * @Author TAO
 * @Date 2017/10/16 22:53
 */
public interface UserService {
    UserT getUserById(int userId);

    int deleteByPrimaryKey(Integer id);

    int insert(UserT record);

    int updateByPrimaryKey(UserT record);

    void bath() throws ActionException;

    void bath2() throws ActionException;
}
package com.tao.springmvc_demo.service.impl;

import ch.qos.logback.core.joran.spi.ActionException;
import com.sun.xml.internal.ws.api.model.CheckedException;
import com.tao.springmvc_demo.service.mapper.UserTMapper;
import com.tao.springmvc_demo.po.UserT;
import com.tao.springmvc_demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author TAO
 * @Date 2017/10/16 22:55
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserTMapper userTMapper;

    @Override
    public UserT getUserById(int userId) {
        return userTMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserT record) {
        UserT userT = new UserT();
        userT.setAge(10);
        userT.setPassword("123");
        userT.setUserName("gutao");
        return userTMapper.insert(userT);
    }

    @Override
    public int updateByPrimaryKey(UserT record) {
        UserT userT1 = new UserT();
        userT1.setId(1);
        userT1.setUserName("yct");
        new HashMap<>(1,1);
        return userTMapper.updateByPrimaryKey(userT1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bath() throws ActionException {
//        UserT userT = new UserT();
//        userT.setAge(10);
//        userT.setPassword("123");
//        userT.setUserName("gutao");
//        userTMapper.insert(userT);
//        UserT userT1 = new UserT();
//        userT1.setId(1);
//        userT1.setUserName("yct");
//        userTMapper.updateByPrimaryKey(userT1);
        insert(null);
        updateByPrimaryKey(null);
        try {

            int i = 1/0;
        }catch (Exception e){
            throw new ActionException();
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bath2() throws ActionException {
        a();
        b();
        int i = 1/0;//事物回滚
    }

    private void a(){
        UserT userT1 = new UserT();
        userT1.setId(1);
        userT1.setUserName("yct");
        userTMapper.updateByPrimaryKey(userT1);

    }

    private void b(){
        UserT userT = new UserT();
        userT.setAge(10);
        userT.setPassword("123");
        userT.setUserName("gutao");
        userTMapper.insert(userT);
    }

}

package com.tao.springmvc_demo;

import ch.qos.logback.core.joran.spi.ActionException;
import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.log.LogFactory;
import com.tao.springmvc_demo.po.UserT;
import com.tao.springmvc_demo.service.UserService;
import com.tao.springmvc_demo.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author TAO
 * @Date 2017/10/16 22:59
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
    private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
    //  private ApplicationContext ac = null;
    @Resource
    private UserService userService;

//  @Before
//  public void before() {
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//      userService = (IUserService) ac.getBean("userService");
//  }

    @Test
    public void test1() {
        UserT user = userService.getUserById(1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
    }

    @Test
    public void test2() throws ActionException {
        userService.bath2();
    }
}

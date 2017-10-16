package com.tao.springmvc_demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author TAO
 * @Date 2017/9/23 14:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/resources")
@ContextHierarchy({
        @ContextConfiguration(name = "child", classes = MyMvcConfig.class)
})
public class HelloTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /**
     *  1、mockMvc.perform执行一个请求；

         2、MockMvcRequestBuilders.get("/user/1")构造一个请求

         3、ResultActions.andExpect添加执行完成后的断言

         4、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。

         5、ResultActions.andReturn表示执行完成后返回相应的结果。
     */
    @Test
    public void testView() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(mvcResult.getModelAndView().getModel().get("msg"));
    }

    @Test
    public void testView2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/anno/pathvar/{str}","zanwei"))
                .andDo(MockMvcResultHandlers.print())
                ;

    }

    @Test
    public void testView3() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/anno/obj").param("id","10").param("name","wang"))
//                .andDo(MockMvcResultHandlers.print())
//        ;

        mockMvc.perform(MockMvcRequestBuilders.post("/anno/obj?id=10&name=wang"))
                .andDo(MockMvcResultHandlers.print())
        ;

    }


    @Test
    public void testView4() throws Exception {
        String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/anno/obj2")
                .contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }




}

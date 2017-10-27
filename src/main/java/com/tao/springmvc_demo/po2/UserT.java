package com.tao.springmvc_demo.po2;

import com.tao.springmvc_demo.common.BaseEntity;

public class UserT extends BaseEntity {
    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    *
     * @return id
     
    public Integer getId() {
        return id;
    }

    *
     * @param id
     
    public void setId(Integer id) {
        this.id = id;
    }

    *
     * @return user_name
     
    public String getUserName() {
        return userName;
    }

    *
     * @param userName
     
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    *
     * @return password
     
    public String getPassword() {
        return password;
    }

    *
     * @param password
     
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    *
     * @return age
     
    public Integer getAge() {
        return age;
    }

    *
     * @param age
     
    public void setAge(Integer age) {
        this.age = age;
    }
}
package com.tao.springmvc_demo.mapper;

import com.tao.springmvc_demo.po.UserT;

public interface UserTMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserT record);

    int insertSelective(UserT record);

    UserT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserT record);

    int updateByPrimaryKey(UserT record);
}
package com.tao.springmvc_demo.common;

import java.io.Serializable;

/**
 * @Author TAO
 * @Date 2017/10/27 11:13
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

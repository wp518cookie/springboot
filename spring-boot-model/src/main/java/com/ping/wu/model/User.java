package com.ping.wu.model;

import java.io.Serializable;

/**
 * Created by ping.wu on 2018/4/27.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -4787426264491507478L;
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

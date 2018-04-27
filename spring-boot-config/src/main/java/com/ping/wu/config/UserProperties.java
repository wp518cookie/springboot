package com.ping.wu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ping.wu on 2018/4/27.
 */
@Component
public class UserProperties {
    @Value("${test.user.name}")
    private String name;
    @Value("${test.user.age}")
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}

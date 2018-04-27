package com.ping.wu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by ping.wu on 2018/4/27.
 */
@Component
@ConfigurationProperties(prefix = "user1")
//@PropertySource("classpath:application.yml")
public class UserConfig {
    private String name;
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

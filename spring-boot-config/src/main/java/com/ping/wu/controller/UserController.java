package com.ping.wu.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ping.wu.config.UserConfig;
import com.ping.wu.config.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ping.wu on 2018/4/27.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserProperties userProperties;
    @Autowired
    private UserConfig userConfig;

    @GetMapping(path = {""})
    public String listUser() {
        return userConfig.getName() + " : " + userConfig.getAge();
    }
}

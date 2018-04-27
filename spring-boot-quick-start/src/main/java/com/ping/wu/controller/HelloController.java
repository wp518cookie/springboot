package com.ping.wu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ping.wu on 2018/4/27.
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String testHello(@PathVariable String name) {
        return "hello " + name;
    }
}

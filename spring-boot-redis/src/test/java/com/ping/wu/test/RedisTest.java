package com.ping.wu.test;

import com.ping.wu.BootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ping.wu on 2018/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootStrap.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello", "world");
        System.out.println(operations.get("hello"));
    }
}

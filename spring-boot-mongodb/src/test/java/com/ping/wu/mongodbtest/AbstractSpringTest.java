package com.ping.wu.mongodbtest;

import com.ping.wu.BootStrap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by ping.wu on 2018/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootStrap.class)
@WebAppConfiguration
/**
 * If you have any property file to load to test uncomment below line)
 @TestPropertySource({
 "classpath:/properties/dbConfig-test.properties",
 "classpath:/properties/unittest.properties"
 })
 */
public abstract class AbstractSpringTest{}

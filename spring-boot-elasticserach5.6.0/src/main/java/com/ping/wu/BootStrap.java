package com.ping.wu;

import com.ping.wu.config.EsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by ping.wu on 2018/4/28.
 */
@SpringBootApplication
@Import(EsClientConfig.class)
public class BootStrap {
    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
    }
}

package com.ping.wu.test;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;

/**
 * Created by ping.wu on 2018/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticInitialTest {
    @Test
    public void testInitial() throws Exception {
        // 配置客户端连接ES的集群名称
        Settings settings = Settings.builder()
                .put("cluster.name", "es-cluster")
                .build();

        // 创建TransportClient对象
        TransportClient.Builder builder = TransportClient.builder();
        builder.settings(settings);
        TransportClient client = builder.build();
        // 解析application.properties配置的ES连接地址, 例如 10.19.180.200:9300,10.19.180.192:9300
        // 1. 多地址用逗号分隔
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("106.14.115.150"), Integer.valueOf(9301)));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.98.112.30"), Integer.valueOf(9300)));
        System.out.println(client.toString());
    }
}

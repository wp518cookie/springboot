package com.ping.wu.esclient;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ping.wu on 2018/4/28.
 */
@Configuration
public class EsClientConfig {
    // 读取applicatio.properties的配置
        @Value("${spring.data.elasticsearch.clusterNodes}")
        private String clusterNodes;

        @Value("${spring.data.elasticsearch.clusterName}")
        private String clusterName;

        @Bean
        public TransportClient elasticsearchClient() throws UnknownHostException {
            try {
                // 配置客户端连接ES的集群名称
                Settings settings = Settings.builder()
                        .put("cluster.name", clusterName)
                        .build();
                // 创建TransportClient对象
                TransportClient client = new PreBuiltTransportClient(settings);
                // 解析application.properties配置的ES连接地址, 例如 10.19.180.200:9300,10.19.180.192:9300
                // 1. 多地址用逗号分隔
                String[] nodes = clusterNodes.split(",");
                for (int i = 0; i < nodes.length; i++) {
                    // 冒号分割ip和端口号
                    String[] hostport = nodes[i].split(":");
                    String host = hostport[0];
                    String port = hostport[1];
                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), Integer.valueOf(port)));
                }
                return client;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}

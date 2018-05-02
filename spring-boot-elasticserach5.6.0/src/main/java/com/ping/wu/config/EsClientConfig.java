package com.ping.wu.config;

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
 * Created by ping.wu on 2018/5/2.
 */
@Configuration
public class EsClientConfig {
    @Value("${spring.data.elasticsearch.clusterNodes}")
    private String clusterNodes;

    @Value("${spring.data.elasticsearch.clusterName}")
    private String clusterName;

    @Bean
    public TransportClient elasticsearchClient() throws UnknownHostException {
        try {
            // 配置客户端连接ES的集群名称
            Settings setting = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff", true)
                    .build();
            TransportClient client = new PreBuiltTransportClient(setting);
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
            return null;
        }
    }
}

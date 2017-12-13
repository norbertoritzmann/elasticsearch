package com.elastic.test;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.elastic.test")
@Slf4j
public class Config {

	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

//	@Bean
//    public Client client() throws Exception {
//
//        Settings esSettings = Settings.settingsBuilder()
//                .put("cluster.name", esClusterName)
//                .build();
//
//        return TransportClient.builder()
//                .settings(esSettings)
//                .build();
//    }

//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }

}
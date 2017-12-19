package com.elastic.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
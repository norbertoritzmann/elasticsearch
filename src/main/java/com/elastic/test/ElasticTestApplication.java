package com.elastic.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.elastic.test.model.DocumentFile;
import com.elastic.test.model.DocumentFileRepository;
import com.elastic.test.model.PermissionGroup;

@SpringBootApplication
@EnableScheduling
public class ElasticTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticTestApplication.class, args);
	}
	
	@Autowired
	private ElasticsearchTemplate template;
	
	@Autowired
	private DocumentFileRepository repository;
	
	@Scheduled(fixedDelay = 50000000)
	public void start() {
		template.createIndex(DocumentFile.class);
		
		DocumentFile article = new DocumentFile("1", "tenant1", "Spring Data Elasticsearch", Arrays.asList(new PermissionGroup("admins", Arrays.asList(1l,2l,3l,4l,5l))));
		repository.save(article);
	}
}

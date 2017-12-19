package com.elastic.test.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.domain.*;

public interface DocumentFileRepository extends ElasticsearchRepository<DocumentFile, String> {
	
	Page<DocumentFile> findByContentAndReadPermissionGroups_users(String content, Integer user, Pageable pageable);
	
}

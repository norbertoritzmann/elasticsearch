package com.elastic.test.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.domain.*;

public interface DocumentFileRepository extends ElasticsearchRepository<DocumentFile, String> {
	
	Page<DocumentFile> findByContentAndReadPermissionGroups_users(String name, Integer userCode, Pageable pageable);
	
}

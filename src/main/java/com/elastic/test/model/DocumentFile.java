package com.elastic.test.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"readPermissionGroups"})
@Document(indexName = "document", type = "article")
public class DocumentFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private String oid;

    private String context;

    private String content;

    @Field(type = FieldType.Nested)
    private List<PermissionGroup> readPermissionGroups;

}

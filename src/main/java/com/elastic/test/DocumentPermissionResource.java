package com.elastic.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.elastic.test.model.DocumentFile;
import com.elastic.test.model.DocumentFileRepository;
import com.elastic.test.model.PermissionGroup;

@RestController
@RequestMapping("/permissions")
public class DocumentPermissionResource {

	@Autowired
	private DocumentFileRepository repository;

	@PostMapping("/{userCode}")
	public List<DocumentFile> findPermittedFiles(@RequestBody String contentFragment, @PathVariable Integer userCode, Pageable page) {
		Page<DocumentFile> docs = repository.findByContentAndReadPermissionGroups_users(contentFragment, userCode, page);
		
		return docs.getContent();
	}

	@GetMapping("/indexFiles")
	public List<String> parseToStringExample() throws IOException, SAXException, TikaException {
		Tika tika = new Tika();
		Stream<Path> files = Files.list(Paths.get("docs"));
		 List<String> filesPaths = new ArrayList<>();
		// files.forEach(p ->
		// filesPaths.add(p.toAbsolutePath().toFile().getAbsolutePath()));

		List<PermissionGroup> groups = generateRandomPermissionGroups(5);
		for (Path file : files.collect(Collectors.toList())) {
			List<PermissionGroup> filePermissions30percent = groups.stream().filter(p -> Math.random() > 0.3)
					.collect(Collectors.toList());
			InputStream is = Files.newInputStream(file);
			String content = tika.parseToString(is);

			DocumentFile document = new DocumentFile();
			document.setContent(content);
			document.setContext("localhost");
			document.setOid(file.toFile().getName());
			document.setReadPermissionGroups(filePermissions30percent);

			repository.save(document);
			
			filesPaths.add(file.toFile().getName());
		}

		files.close();

		return filesPaths;
	}

	private List<PermissionGroup> generateRandomPermissionGroups(Integer size) {
		List<PermissionGroup> permissionGroups = new ArrayList<>();
		Random random = new Random(1l);

		for (int index = 0; index < size; index++) {
			List<Long> userCodes = new ArrayList<>();

			for (int userIndex = 0; userIndex < size; userIndex++) {
				Integer userCode = random.nextInt(200);

				userCodes.add(Integer.toUnsignedLong(userCode));
			}

			permissionGroups.add(new PermissionGroup("" + index, userCodes));
		}

		return permissionGroups;
	}
}

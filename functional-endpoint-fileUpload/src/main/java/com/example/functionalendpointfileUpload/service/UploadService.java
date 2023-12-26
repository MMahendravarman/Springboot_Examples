package com.example.functionalendpointfileUpload.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import com.couchbase.client.core.deps.com.google.common.base.Supplier;
import com.example.functionalendpointfileUpload.entity.Employee;
import com.example.functionalendpointfileUpload.repository.UploadRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class UploadService {
	
	@Autowired
	UploadRepository repo;

	public Flux<Employee> upload(MultiValueMap<String, Part> file){

		log.info("file upload started...");
		Map<String, Part> part = file.toSingleValueMap();
		FilePart filePart = (FilePart) part.get("file");		
		return DataBufferUtils.join(filePart.content()).map(data ->extracted(data)).
				map(this::processFile).
				flatMapMany(row -> {
			List<Employee> employees = new ArrayList<>();
			for(int i=0;i<row.size();i++) {
				String[] nextLine = row.get(i).split(",");
				Employee emp = Employee.builder().name(nextLine[0]).department(nextLine[1]).build();
				employees.add(emp);				
			}
			return repo.saveAll(employees);
		});
	}

	private String extracted(DataBuffer data) {
		byte[] bytes = data.asByteBuffer().array();
		data.read(bytes);
		DataBufferUtils.release(data);
		return new String(bytes,StandardCharsets.UTF_8);
	}
	
	private List<String> processFile(String line){
		Supplier<Stream<String>> lines = line::lines;
		return lines.get().collect(Collectors.toList());
		
	}
}

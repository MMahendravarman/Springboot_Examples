package com.example.awsspringS3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.example.awsspringS3.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;


@RestController
@Slf4j
public class S3FileUploadController {
	@Autowired
	UploadService service;
	
	@PostMapping(path="/upload", consumes="multipart/form-data")
	public Flux<Object> fileUpload(@RequestPart(name="file",required=true) FilePart file){
		log.info("inside controller");
		return service.csvFileUpload(file);
		
	}
}

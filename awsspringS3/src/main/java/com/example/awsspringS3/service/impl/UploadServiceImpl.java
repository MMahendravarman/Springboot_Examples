package com.example.awsspringS3.service.impl;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import com.example.awsspringS3.service.UploadService;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

	@Autowired
	S3Template s3Template;
	
	@Value("${BUCKET_NAME}")
	String bucketName;

	@Override
	public Flux<Object> csvFileUpload(FilePart filePart) {
		log.info("inside csvFileUpload" + filePart.filename());

		return filePart.content().map(dataBuffer -> {

			InputStream is = dataBuffer.asInputStream();
			S3Resource resource = s3Template.upload(bucketName, "employees.csv", is);
			try {	
				return resource.getURL();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Mono.error(e);
			}

		});

	}

}

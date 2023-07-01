package com.example.awsspringS3.service;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface UploadService {

	Flux<Object> csvFileUpload(FilePart file);

}

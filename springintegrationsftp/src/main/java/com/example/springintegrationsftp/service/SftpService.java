package com.example.springintegrationsftp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import com.example.springintegrationsftp.config.SftpConfig;

@Service
public class SftpService {

	@Autowired
	SftpConfig.CustomGateway sftpGateway;

    public void uploadFileToSftp() {    	
  	
    	sftpGateway.sendToSftp(new FileSystemResource("D://sftp.txt").getFile());
    	
    }
}

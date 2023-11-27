package com.example.springintegrationsftp.service;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
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

package com.example.springintegrationsftp.config;

import java.io.File;

import org.apache.sshd.sftp.client.SftpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class SftpConfig {
	
	@Value("${sftp.host}")
	private  String host;
	@Value("${sftp.user}")
	private String user;
	@Value("${sftp.password}")
	private String password;

    @Bean
    public SessionFactory<SftpClient.DirEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost(host);
        factory.setPort(22);
        factory.setUser(user);
        factory.setPassword(password);
        factory.setAllowUnknownKeys(true);
        return new CachingSessionFactory<>(factory);
    }

    @Bean
    @ServiceActivator(inputChannel = "toSftpChannel")
    public MessageHandler handler() {
        SftpMessageHandler handler = new SftpMessageHandler(sftpSessionFactory());
        handler.setRemoteDirectoryExpression(new LiteralExpression("/"));
        handler.setLoggingEnabled(true);
        handler.setFileNameGenerator(new FileNameGenerator() {
            @Override
            public String generateFileName(Message<?> message) {
                if (message.getPayload() instanceof File) {
                    return ((File) message.getPayload()).getName();
                } else throw new IllegalArgumentException("File expected in message payload");
            }
        });
        return handler;
    }

    @MessagingGateway(asyncExecutor = "sftpAsync")
    public interface CustomGateway {
        @Gateway(requestChannel = "toSftpChannel")
        void sendToSftp(File file);
    }
    
    @Bean
    public AsyncTaskExecutor sftpAsync() {
    	SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
    	simpleAsyncTaskExecutor.setThreadNamePrefix("sftp-exec-");
    	return simpleAsyncTaskExecutor;
    }
}

package com.stp.xxx.config.hook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class Creator implements ApplicationRunner {

    // 通过配置文件获取端口号
    @Value("${server.port}")
    private String port;

    @Override
    public void run(ApplicationArguments args) {
        try {
            // 获取本机IP地址
            String ipAddress = InetAddress.getLocalHost().getHostAddress();

            // 打印文档地址
            log.info("API documentation available at: http://localhost:" + port + "/doc.html");
            log.info("API documentation available at: http://" + ipAddress + ":" + port + "/doc.html");
        } catch (UnknownHostException e) {
            log.info("Error retrieving IP address");
            throw new RuntimeException(e);
        }
    }
}
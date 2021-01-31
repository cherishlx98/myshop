package com.lx.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * 启动类
 */
@SpringBootApplication
@ImportResource(locations = "classpath:dubbo/consumer.xml")
public class ApiGatewayApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApiGatewayApplication.class,args);

        System.in.read();
    }
}

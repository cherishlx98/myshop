package com.lx.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource(locations = "classpath:dubbo/provider.xml")
public class OrderServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(OrderServiceApplication.class,args);

        System.in.read();
    }
}

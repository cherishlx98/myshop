package com.lx.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource(locations = "classpath:dubbo/provider.xml")
public class GoodServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(GoodServiceApplication.class,args);

        System.in.read();
    }
}

package com.zhixi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class Springboot04SwaggerApplication {

    public static void main(String[] args) {

        SpringApplication.run(Springboot04SwaggerApplication.class, args);
    }
}

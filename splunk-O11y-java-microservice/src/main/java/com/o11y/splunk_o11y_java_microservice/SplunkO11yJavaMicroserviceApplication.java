package com.o11y.splunk_o11y_java_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@SpringBootApplication
public class SplunkO11yJavaMicroserviceApplication {

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        SpringApplication.run(SplunkO11yJavaMicroserviceApplication.class, args);
    }



    @RestController
    @RequestMapping("/api")
    public class ApiController {
        private final RestTemplate restTemplate;
        private final String goServiceUrl;

        public ApiController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
            this.goServiceUrl = new File("src/main/resources/main").getAbsolutePath();
        }

        @GetMapping("/greeting")
        public String getGreeting() {
            String response = restTemplate.getForObject("http://localhost:8080/greeting", String.class);
            return "Spring Boot says: " + response;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

package com.company.procuraX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProcuraXApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcuraXApplication.class, args);
    }
}

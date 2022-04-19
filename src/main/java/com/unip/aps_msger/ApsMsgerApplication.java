package com.unip.aps_msger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories @Configuration
@SpringBootApplication
public class ApsMsgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApsMsgerApplication.class, args);
    }

    @Autowired
    public ApsMsgerApplication() {
        new WebsocketServer().start();
    }
}

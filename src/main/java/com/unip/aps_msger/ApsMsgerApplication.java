package com.unip.aps_msger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApsMsgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApsMsgerApplication.class, args);
    }

    @Autowired
    public ApsMsgerApplication(){
        WebsocketServer chatserver = new WebsocketServer();
        chatserver.start();
    }
}

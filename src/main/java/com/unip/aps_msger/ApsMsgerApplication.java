package com.unip.aps_msger;

import com.unip.aps_msger.ssl.ChatServer;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.security.KeyStore;


@SpringBootApplication
public class ApsMsgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApsMsgerApplication.class, args);
    }

    @Autowired
    public ApsMsgerApplication() throws Exception {

        ChatServer chatserver = new ChatServer(443); // Firefox does allow multible ssl connection only via port 443 //tested on FF16

        // load up the key store
        String STORETYPE = "JKS";
        String KEYSTORE = Paths.get("test",  "keystore.jks")
                .toString();
        String STOREPASSWORD = "storepassword";
        String KEYPASSWORD = "keypassword";

        KeyStore ks = KeyStore.getInstance(STORETYPE);
        File kf = new File(KEYSTORE);
        System.out.println("kf : " + kf.exists());
        ks.load(new FileInputStream(kf), STOREPASSWORD.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, KEYPASSWORD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);

        SSLContext sslContext;
        sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        chatserver.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(sslContext));

        chatserver.start();
    }
}

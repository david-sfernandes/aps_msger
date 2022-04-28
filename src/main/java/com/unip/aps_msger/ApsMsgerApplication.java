package com.unip.aps_msger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication @CrossOrigin
public class ApsMsgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApsMsgerApplication.class, args);
    }

    @Autowired
    public ApsMsgerApplication(){
        WebsocketServer chatserver = new WebsocketServer(); // Firefox does allow multible ssl connection only via port 443 //tested on FF16
        // load up the key store
//        String STORETYPE = "JKS";
//        String KEYSTORE = Paths.get("test",  "keystore.jks")
//                .toString();
//        String STOREPASSWORD = "storepassword";
//        String KEYPASSWORD = "keypassword";
//
//        KeyStore ks = KeyStore.getInstance(STORETYPE);
//        File kf = new File(KEYSTORE);
//        System.out.println("kf : " + kf.exists());
//        ks.load(new FileInputStream(kf), STOREPASSWORD.toCharArray());
//
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//        kmf.init(ks, KEYPASSWORD.toCharArray());
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
//        tmf.init(ks);
//
//        SSLContext sslContext;
//        sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//        chatserver.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(sslContext));
        chatserver.start();
    }
}

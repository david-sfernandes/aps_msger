package com.unip.aps_msger;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component @ServerEndpoint(value = "/")
public class WebsocketServer {
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        System.out.println("Welcome! New session open : " + session);
        System.out.println("Used endpointConfig : " + endpointConfig + "\n");
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println(session + " :: " + msg  + "\n");
        try {
            for (Session sess : session.getOpenSessions()) {
                if (sess.isOpen())
                    sess.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            System.out.println("Error to send message!");
            e.printStackTrace();
        }
    }
}

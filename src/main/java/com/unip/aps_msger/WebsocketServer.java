package com.unip.aps_msger;

import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component @ServerEndpoint(value = "/")
public class WebsocketServer {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("\nWelcome! New session open : " + session);
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println(session.getId() + " :: " + msg  + "\n");
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

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error from session" + session.getId());
        throwable.printStackTrace();
    }
}
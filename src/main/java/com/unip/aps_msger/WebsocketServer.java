package com.unip.aps_msger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unip.aps_msger.model.Message;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.*;

@Configuration
public class WebsocketServer extends WebSocketServer {
    private static final int TCP_PORT = 443;

    Set<WebSocket> connections;
    ObjectMapper mapper;

    @Autowired
    public WebsocketServer() {
        super(new InetSocketAddress(TCP_PORT));
        connections = new HashSet<>();
        mapper = new ObjectMapper();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome! Log in success.");
        connections.add(conn);
        System.out.println("New enter the server : " + conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
        System.out.println("Host : " + conn.getLocalSocketAddress().getHostName());
        System.out.println("Port : " + conn.getLocalSocketAddress().getPort());

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        sendToAll(webSocket, s);
        Message msg = new Message();
        try {
            msg = mapper.readValue(s, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(webSocket+ ": " + msg.getText());
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        broadcast(message.array());
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            System.out.println("ERROR from " + conn);
        }
    }

    @Override
    public void onStart() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Server started on port: " + this.getPort());
        System.out.println("Server address: " + this.getAddress());
    }

    public void sendToAll (WebSocket conn, String s) {
        Set<WebSocket> copyConnections = connections;
        copyConnections.remove(conn);
        this.broadcast(s, copyConnections);
    }
}

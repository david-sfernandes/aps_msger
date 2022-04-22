package com.unip.aps_msger;

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
    private static final int TCP_PORT = 4449;
    Set<WebSocket> connections;

    @Autowired
    public WebsocketServer() {
        super(new InetSocketAddress(TCP_PORT));
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        connection.send("Welcome! Log in success.");
        connections.add(connection);
        System.out.println("New connection from " + connection.getRemoteSocketAddress().getAddress().getHostAddress());
        broadcast("New connection : " + handshake.getResourceDescriptor());
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
        broadcast(s);
        System.out.println(webSocket + " ==> " + s);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        broadcast(message.array());
        System.out.println(conn + " ==> " + message);
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
        System.out.println("Server started!");
    }
}

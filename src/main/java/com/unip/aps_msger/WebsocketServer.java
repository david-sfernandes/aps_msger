package com.unip.aps_msger;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


//@Configuration
//public class WebsocketServer extends WebSocketServer {
//    private static final int TCP_PORT = 443;
//    Set<WebSocket> connections;
//
//    @Autowired
//    public WebsocketServer() {
//        super(new InetSocketAddress(TCP_PORT));
//        connections = new HashSet<>();
//    }
//
//    @Override
//    public void onOpen(WebSocket conn, ClientHandshake handshake) {
//        conn.send("Welcome! Log in success.");
//        connections.add(conn);
//        System.out.println("New connection from : " + conn);
//    }
//
//    @Override
//    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
//        connections.remove(conn);
//        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
//        System.out.println("Host : " + conn.getLocalSocketAddress().getHostName());
//        System.out.println("Port : " + conn.getLocalSocketAddress().getPort());
//    }
//
//    @Override
//    public void onMessage(WebSocket webSocket, String s) {
//        broadcast(s);
//        System.out.println(webSocket + ": " + s);
//    }
//
//    @Override
//    public void onMessage(WebSocket conn, ByteBuffer message) {
//        broadcast(message.array());
//        System.out.println(conn + ": " + message);
//    }
//
//    @Override
//    public void onError(WebSocket conn, Exception ex) {
//        if (conn != null) {
//            System.out.println("ERROR in connection: " + conn.getLocalSocketAddress());
//            ex.printStackTrace();
//        } else {
//            System.out.println("Null connection error!");
//        }
//    }
//
//    @Override
//    public void onStart() {
//        System.out.println("Server started at port " + this.getPort());
//        setConnectionLostTimeout(0);
//        setConnectionLostTimeout(1000);
//    }
//}

@Controller
public class WebsocketServer {
    @MessageMapping("/s")
    @SendTo("/to/all")
    public Message sendMsg(Message message){
        System.out.println("Recebi: " + message.getText());
        return new Message("Server recebeu: " + message.getText());
    }
}
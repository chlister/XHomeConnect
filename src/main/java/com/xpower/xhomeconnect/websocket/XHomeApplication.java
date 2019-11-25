package com.xpower.xhomeconnect.websocket;
/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/25/19
 */


import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;

import java.util.ArrayList;
import java.util.List;

public class XHomeApplication extends WebSocketApplication implements IWebSocketApplication {

    private List<WebSocket> clients;

    public XHomeApplication() {
        clients = new ArrayList<>();
    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     * @param webSocket - the websocket who fired the event
     */
    @Override
    public void onClose(WebSocket webSocket) {

    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     * @param webSocket - the websocket who fired the event
     */
    @Override
    public void onConnect(WebSocket webSocket) {
        System.out.println("Client connected: " + webSocket.toString());
        clients.add(webSocket);
    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     * @param webSocket - the websocket who fired the event
     */
    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Client: " + webSocket + " Sends message: " + message);
        webSocket.send("Hello client!!!!");
    }
}

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/25/19
 */

package com.xpower.xhomeconnect.websocket;

import org.glassfish.grizzly.websockets.WebSocket;


public interface IWebSocketApplication {
    void onConnect(WebSocket webSocket);
    void onClose(WebSocket webSocket);
    void onMessage(WebSocket webSocket, String message);
}

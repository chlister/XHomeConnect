/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.websocket;

public interface IWebSocketManager {

    void onOpen();
    void onClose();
    void onMessage();
    void updateSocket(SocketDTO socketDTO);
}

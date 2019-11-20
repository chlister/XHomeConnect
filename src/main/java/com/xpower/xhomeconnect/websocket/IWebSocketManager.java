/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.websocket;

public interface IWebSocketManager {

    /**
     * listener event when com.xpower.xhomeconnect.websocket opens
     */
    void onOpen();

    /**
     * listener event when the connection closes (failure or otherwise)
     */
    void onClose();

    /**
     * When the client sends a message to the com.xpower.xhomeconnect.websocket
     */
    void onMessage();

    /**
     * When a socket needs to be updated
     * @param socket SocketDTO
     */
    void updateSocket(SocketDTO socketDTO);
}

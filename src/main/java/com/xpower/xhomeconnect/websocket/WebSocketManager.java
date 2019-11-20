/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.websocket;

import com.xpower.xhomeconnect.IWebSocketCallback;

public class WebSocketManager implements IWebSocketManager {

    private IWebSocketCallback callback;

    public WebSocketManager(IWebSocketCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void onMessage() {

    }

    @Override
    public void updateSocket(SocketDTO socket) {

    }
}

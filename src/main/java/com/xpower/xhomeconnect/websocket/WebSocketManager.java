/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.websocket;

import com.xpower.xhomeconnect.IWebSocketCallback;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;

public class WebSocketManager extends WebSocketApplication implements IWebSocketManager  {

    private IWebSocketCallback callback;

    public WebSocketManager(IWebSocketCallback callback) {
        this.callback = callback;
    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void onOpen() {

    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void onConnect(WebSocket socket) {
        System.out.println("Connected client: " + socket);
    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void onClose() {

    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void onMessage() {

    }

    /**
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void updateSocket(SocketDTO socket) {

    }
}

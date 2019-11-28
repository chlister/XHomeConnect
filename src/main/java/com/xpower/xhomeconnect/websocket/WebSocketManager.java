/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.websocket;

import com.google.gson.internal.LinkedTreeMap;
import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IWebSocketCallback;
import com.xpower.message.model.SocketDTO;
import org.glassfish.grizzly.websockets.DataFrame;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;

import java.util.ArrayList;
import java.util.List;

public class WebSocketManager extends WebSocketApplication {

    private IWebSocketCallback callback;

    public WebSocketManager(IWebSocketCallback callback) {
        this.callback = callback;
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void onConnect(WebSocket socket) {
        System.out.println("Connected client: " + socket);
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void onClose(WebSocket socket, DataFrame frame) {
        System.out.println("Closing connection: " + socket);
    }

    /**
     * @author Marc R. K.
     * @version 0.3
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void onMessage(WebSocket socket, String json) {
        System.out.println("Message from " + socket + " [" + json + "]");
        Message message = new Message(json);
        switch (message.getMethodCode()) {
            case REGISTER:
                callback.registerSocket(SocketDTO.deserialize((LinkedTreeMap) message.getObj()));
                break;
            case GET_SOCKETS:
                callback.getSockets(socket);
                break;
//            case DETECT_AGENTS:
//                callback.detectLocalAgents();
//                break;
            default:
                break;
        }

    }

    @Override
    protected boolean onError(WebSocket webSocket, Throwable t) {
        System.out.println("Socket encountered an error: " + t.getMessage());
        return super.onError(webSocket, t);
    }

    /**
     * @author Marc R. K.
     * @version 0.3
     * @status Defined
     * @since 11/20/19
     */
    public void returnSockets(WebSocket socket, RespondCodes respondCodes, List<SocketDTO> sockets) {
        Message message = new Message(respondCodes, MethodCode.GET_SOCKETS, sockets);
        socket.send(message.encode());
    }
}

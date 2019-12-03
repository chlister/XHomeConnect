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
import com.xpower.message.model.OutletDTO;
import org.glassfish.grizzly.websockets.DataFrame;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;

import java.util.ArrayList;
import java.util.List;

public class WebSocketManager extends WebSocketApplication {

    private IWebSocketCallback callback;
    private List<WebSocket> clients;

    public WebSocketManager(IWebSocketCallback callback) {
        clients = new ArrayList<>();
        this.callback = callback;
    }

    /**
     * @author Marc R. K.
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void onConnect(WebSocket socket) {
        System.out.println("Connected client: " + socket);
        clients.add(socket);
    }

    /**
     * @author Marc R. K.
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void onClose(WebSocket socket, DataFrame frame) {
        System.out.println("Closing connection: " + socket);
        clients.remove(socket);
    }

    /**
     * The onMessage event handles a collection of commands.
     *
     * @author Marc R. K.
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void onMessage(WebSocket socket, String json) {
        System.out.println("Message from " + socket + " [" + json + "]");
        Message message;
        try {
            message = new Message(json);
            switch (message.getMethodCode()) {
                case REGISTER:
                    OutletDTO o = OutletDTO.deserialize((LinkedTreeMap) message.getObj());
                    if (!o.getApplianceType().equals("NON")) {
                        callback.registerOutlet(OutletDTO.deserialize((LinkedTreeMap) message.getObj()));
                        socket.send(new Message(RespondCodes.OK, MethodCode.REGISTER, null).encode());
                    } else
                        socket.send(new Message(RespondCodes.NOT_FOUND, MethodCode.REGISTER, null).encode());
                    break;
                case GET_SOCKETS:
                    callback.getSockets(socket);
                    break;
                case CHANGE_SOCKET_STATE:
                    callback.changeState(OutletDTO.deserialize((LinkedTreeMap) message.getObj()));
                    break;
                default:
                    socket.send("The server couldn't read the message");
                    System.out.println("default switch case");
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("JSON doesn't match convention");
            System.out.println(json);
        }
    }

    @Override
    protected boolean onError(WebSocket webSocket, Throwable t) {
        System.out.println("Socket encountered an error: " + t.getMessage());
        return super.onError(webSocket, t);
    }

    /**
     * @author Marc R. K.
     * @status Defined
     * @since 11/20/19
     */
    public void returnSockets(WebSocket webSocket, RespondCodes respondCodes, List<OutletDTO> outlets) {
        Message message = new Message(respondCodes, MethodCode.GET_SOCKETS, outlets);
        webSocket.send(message.encode());
    }

    public void outletChangedEvent(List<OutletDTO> outlets, RespondCodes response) {
        if (!clients.isEmpty()) {

            for (WebSocket client :
                    clients) {
                returnSockets(client, response, outlets);
            }
        }
    }
}

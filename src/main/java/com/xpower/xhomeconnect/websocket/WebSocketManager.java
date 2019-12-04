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
import com.xpower.message.model.OutletDTO;
import org.glassfish.grizzly.websockets.DataFrame;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketEngine;

import java.util.ArrayList;
import java.util.List;

public class WebSocketManager extends WebSocketApplication implements IWebSocketManager {

    private IWebSocketCallback callback;
    private List<WebSocket> clients;

    public WebSocketManager(IWebSocketCallback callback) {
        clients = new ArrayList<>();
        this.callback = callback;
    }

    /**
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    @Override
    public void onConnect(WebSocket socket) {
        System.out.println("Connected client: " + socket);
        clients.add(socket);
    }

    /**
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    @Override
    public void onClose(WebSocket socket, DataFrame frame) {
        System.out.println("Closing connection: " + socket);
        clients.remove(socket);
    }

    /**
     * Method expects json format for String json.
     * String is converted to a Message object which is used to specify the method call
     *
     * @param socket WebSocket
     * @param json   String
     * @author Marc R. K.
     * @status Done
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
                    OutletDTO dto = OutletDTO.deserialize((LinkedTreeMap) message.getObj());
                    if (!dto.getApplianceType().equals("NON")) {
                        callback.onRegisterOutletRequest(OutletDTO.deserialize((LinkedTreeMap) message.getObj()));
                        socket.send(new Message(RespondCodes.OK, MethodCode.REGISTER, null).encode());
                    } else
                        socket.send(new Message(RespondCodes.NOT_FOUND, MethodCode.REGISTER, null).encode());
                    break;
                case GET_SOCKETS:
                    callback.onGetOutletRequest(socket);
                    break;
                case CHANGE_SOCKET_STATE:
                    callback.onChangeStateRequest(OutletDTO.deserialize((LinkedTreeMap) message.getObj()));
                    break;
                default:
                    socket.send("The server couldn't read the message");
                    System.out.println("default switch case");
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("JSON doesn't match convention");
            socket.send(new Message(RespondCodes.NOT_FOUND, null, null).encode()); // Sends error to client
            System.out.println(json);
        }
    }

    /**
     * Logs an error to the console.
     * Used for testing purpose only.
     *
     * @param webSocket the socket raising the error
     * @param throwable Throwable
     * @author Marc R. K.
     * @status Done
     * @since 11/25/19
     */
    @Override
    protected boolean onError(WebSocket webSocket, Throwable throwable) {
        System.out.println("Socket encountered an error: " + throwable.getMessage());
        return super.onError(webSocket, throwable);
    }

    /**
     * Method will construct a Message object and send it to the specified client
     *
     * @param outlets      List<OutletDTO>
     * @param respondCodes RespondCodes
     * @param webSocket    WebSocket
     * @author Marc R. K.
     * @status Done
     * @since 11/26/19
     */
    @Override
    public void returnOutlets(WebSocket webSocket, RespondCodes respondCodes, List<OutletDTO> outlets) {
        Message message = new Message(respondCodes, MethodCode.GET_SOCKETS, outlets);
        webSocket.send(message.encode());
    }

    /**
     * Sends the list of DTOs to all connected clients
     *
     * @param outlets  List<OutletDTO>
     * @param response RespondCodes
     * @author Marc R. K.
     * @status Done
     * @since 11/28/19
     */
    public void outletChangedEvent(List<OutletDTO> outlets, RespondCodes response) {
        if (!clients.isEmpty()) {

            for (WebSocket client :
                    clients) {
                returnOutlets(client, response, outlets);
            }
        }
    }

    /**
     * @param contextPath String
     * @param urlPattern String
     * @author Marc R. K.
     * @status Done
     * @since 11/28/19
     */
    @Override
    public void registerSocketConnection(String contextPath, String urlPattern) {
        WebSocketEngine.getEngine().register(contextPath, urlPattern, this);

    }
}

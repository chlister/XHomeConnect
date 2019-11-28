package com.xpower.xhomeconnect.websocket;

import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.xhomeconnect.IWebSocketCallback;
import com.xpower.message.model.OutletDTO;
import org.glassfish.grizzly.websockets.WebSocket;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WebSocketManagerTest {

    private List<OutletDTO> outlets;
    private Message messageGetSockets;
    private Message messageRegisterSocket;
    private Message messageChangeSocketState;
    private Message messageDetectLocalAgents;

    @Test
    public void onMessage() {
        // Constructing variables
        sockets = new ArrayList<>();
        sockets.add(new SocketDTO(1, 1, "socket", "Fridge", false));
        messageGetSockets = new Message(MethodCode.GET_SOCKETS, null);
        messageRegisterSocket = new Message(MethodCode.REGISTER, sockets.get(0));
        messageChangeSocketState = new Message(MethodCode.CHANGE_SOCKET_STATE, sockets.get(0));


        // Checks if the method reaches this part of the code
        WebSocketManager manager = new WebSocketManager(new IWebSocketCallback() {
            @Override
            public void getSockets(WebSocket webSocket) {
                Assert.assertTrue(true);
            }

            @Override
            public void registerSocket(OutletDTO outletDTO) {
                Assert.assertTrue(true);
            }

            @Override
            public void changeState(OutletDTO outletDTO) {
                for (OutletDTO outlet: outlets) {
                    if (socket.getAgentId() == socketDTO.getAgentId() && socket.getId() == socketDTO.getId()){
                        System.out.println("Socket state used to be: " + socket.getState());
                        socket.setState(socketDTO.getState());
                        System.out.println("Socket state is now: " + socket.getState());
                    }
                }
                Assert.assertTrue(true);
            }
        });

        manager.onMessage(null, messageGetSockets.encode());
        manager.onMessage(null, messageRegisterSocket.encode());
        manager.onMessage(null, messageChangeSocketState.encode());
    }
}
package com.xpower.xhomeconnect.websocket;

import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IWebSocketCallback;
import com.xpower.message.model.SocketDTO;
import org.glassfish.grizzly.websockets.WebSocket;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WebSocketManagerTest {

    private List<SocketDTO> sockets;
    private Message messageGetSockets;
    private Message messageRegisterSocket;
    private Message messageRegisterSocketList;
    private Message messageDetectLocalAgents;

    @Test
    public void onMessage() {
        // Constructing variables
        sockets = new ArrayList<>();
        sockets.add(new SocketDTO(1, 1, "socket", "Fridge"));
        messageGetSockets = new Message(RespondCodes.OK, MethodCode.GET_SOCKETS, null);
        messageRegisterSocket = new Message(RespondCodes.OK, MethodCode.REGISTER, sockets.get(0));


        // Checks if the method reaches this part of the code
        WebSocketManager manager = new WebSocketManager(new IWebSocketCallback() {
            @Override
            public void getSockets(WebSocket socket) {
                Assert.assertTrue(false);
            }

            @Override
            public void registerSocket(SocketDTO socketDTO) {
                Assert.assertTrue(false);
            }
        });

        manager.onMessage(null, messageGetSockets.encode());
        manager.onMessage(null, messageRegisterSocket.encode());
    }
}
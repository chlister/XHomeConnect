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

    /**
     * Testing of the onMessage functionality
     * onMessage expects certain methodCodes - these should be tested here.
     */
    @Test
    public void onMessage() {
        // Constructing variables
        outlets = new ArrayList<>();
        outlets.add(new OutletDTO(1, 1, "socket", "Fridge", true));
        messageGetSockets = new Message(null, MethodCode.GET_SOCKETS, null);
        messageRegisterSocket = new Message(null,MethodCode.REGISTER, outlets.get(0));
        messageChangeSocketState = new Message(null,MethodCode.CHANGE_SOCKET_STATE, outlets.get(0));


        // Checks if the method reaches this part of the code
        WebSocketManager manager = new WebSocketManager(new IWebSocketCallback() {
            @Override
            public void getSockets(WebSocket webSocket) {
                Assert.assertTrue(true);
            }

            @Override
            public void registerOutlet(OutletDTO outletDTO) {
                Assert.assertTrue(true);
            }

            @Override
            public void changeState(OutletDTO outletDTO) {
                boolean newState = true;
                for (OutletDTO outlet: outlets) {
                    if (outlet.getAgentId() == outletDTO.getAgentId() && outlet.getId() == outletDTO.getId()){
                        System.out.println("Socket state used to be: " + outlet.getState());
                        outlet.setState(outletDTO.getState());
                        System.out.println("Socket state is now: " + outlet.getState());
                        newState = outlet.getState();
                    }
                }
                Assert.assertTrue((outletDTO.getState() == newState));
            }
        });

        manager.onMessage(null, messageGetSockets.encode());
        manager.onMessage(null, messageRegisterSocket.encode());
        manager.onMessage(null, messageChangeSocketState.encode());
    }
}
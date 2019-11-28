package com.xpower.xhomeconnect;

import com.xpower.message.model.OutletDTO;
import org.glassfish.grizzly.websockets.WebSocket;

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

public interface IWebSocketCallback {

    void getSockets(WebSocket socket);

//    void detectLocalAgents(); // websocket only needs to know about homeSockets

    void registerSocket(OutletDTO outletDTO);
    void registerSocket(OutletDTO outletDTO);
    void changeState(OutletDTO outletDTO);
}

package com.xpower.xhomeconnect.websocket;

import com.xpower.message.RespondCodes;
import com.xpower.message.model.OutletDTO;
import org.glassfish.grizzly.websockets.WebSocket;

import java.util.List;

public interface IWebSocketManager {
    void returnOutlets(WebSocket webSocket, RespondCodes respondCodes, List<OutletDTO> outlets);
    void outletChangedEvent(List<OutletDTO> outlets, RespondCodes response);
    void registerSocketConnection(String contextPath, String urlPattern);
}

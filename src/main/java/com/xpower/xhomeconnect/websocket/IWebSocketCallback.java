package com.xpower.xhomeconnect.websocket;

import com.xpower.message.model.OutletDTO;
import org.glassfish.grizzly.websockets.WebSocket;

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

public interface IWebSocketCallback {

    void onChangeStateRequest(OutletDTO outletDTO);

    void onGetOutletRequest(WebSocket socket);

    void onRegisterOutletRequest(OutletDTO outletDTO);
}

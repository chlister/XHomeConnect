package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.SocketDTO;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.List;

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

public interface IWebSocketCallback {

    List<SocketDTO> getSockets();

    void detectLocalAgents();

    HttpStatus registerSocket(SocketDTO socketDTO);
}

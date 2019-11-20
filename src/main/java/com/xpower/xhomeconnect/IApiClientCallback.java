package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.SocketDTO;
import java.util.List;

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

/**
 * Callback interface for the API connection
 */
public interface IApiClientCallback {

    /**
     * Used to return a list of socketDTO objects
     * @return List<SocketDTO>
     */
    List<SocketDTO>getSockets();

    /**
     * Used to search local net for units matching the Netio agent signature.
     */
    void detectLocalAgents();

    /**
     * Used to register a specific socket.
     * @param socket Socket object containing the info to be updated.
     * @return SocketDTO same object as send to denote success from the receivers end.
     */
    SocketDTO registerSocket(SocketDTO socket);
}


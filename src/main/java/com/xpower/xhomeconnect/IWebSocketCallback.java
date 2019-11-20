package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.SocketDTO;
import org.glassfish.grizzly.http.server.Response;

import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

/**
 * Callback interface
 * Implements following functionality:
 * list<SocketDTO> getSockets()
 * void detectLocalAgents()
 * SocketDTO registerSocket()
 */
public interface IWebSocketCallback {

    /**
     * Used to return a list of socketDTO objects
     * @return List<SocketDTO>
     */
    List<SocketDTO> getSockets();

    /**
     * Used to search local net for units matching the Netio agent signature.
     */
    void detectLocalAgents();

    /**
     * Used to register a specific socket.
     * @param socketDTO Socket object containing the info to be updated.
     * @return SocketDTO same object as send to denote success from the receivers end.
     */
    Response registerSocket(SocketDTO socketDTO);
}

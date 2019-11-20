/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */
package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.IWebSocketManager;
import com.xpower.xhomeconnect.websocket.SocketDTO;
import org.glassfish.grizzly.http.server.Response;

import java.util.List;

/**
 * Main class of the XHomeConnect module.
 */
public class HomeController implements IWebSocketCallback {
    IAgentManager agentManager; // TODO class not defined
    IWebSocketManager webSocketManager; //TODO class not defined
//    IApiClientManager apiClientManager; - TODO class not defined

    /**
     * Initialises the XHomeConnect module.
     * @author  Marc R. K.
     * @version 1.0
     * @since   11/20/19
     * @status  Defined
     */
    void Init() {

    }

    /**
     * Used to return a list of socketDTOs
     * @author  Marc R. K.
     * @version 1.0
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public List<SocketDTO> getSockets() {
        return agentManager.getSockets();
    }

    /**
     * Used to search local net for units matching the Netio agent signature.
     * @author  Marc R. K.
     * @version 1.0
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void detectLocalAgents() {
        agentManager.scanNetwork();
    }

    /**
     * Used to register a specific socket.
     * @author  Marc R. K.
     * @version 1.0
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public Response registerSocket(SocketDTO socketDTO) {
        agentManager.updateSocket(socketDTO);
        return null;
    }
}




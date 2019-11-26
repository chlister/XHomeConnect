/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.xhomeconnect.agent;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IAgentGetSocketsCallback;
import com.xpower.message.model.SocketDTO;

import java.util.ArrayList;
import java.util.List;

public class AgentManager implements IAgentManager {

    IAgentGetSocketsCallback agentGetSocketsCallback;

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public AgentManager(IAgentGetSocketsCallback agentGetSocketsCallback) {
        this.agentGetSocketsCallback = agentGetSocketsCallback;
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void getSockets() {
        List<SocketDTO> socketDTOS = new ArrayList<>();
        agentGetSocketsCallback.getSockets(socketDTOS, RespondCodes.OK);
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public void updateSocket(SocketDTO socketDTO) {

    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public List<String> scanNetwork() {
        return null;
    }
}

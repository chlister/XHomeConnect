/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.xhomeconnect.agent;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IAgentGetSocketsCallback;
import com.xpower.message.model.OutletDTO;

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
        List<OutletDTO> outletDTOS = new ArrayList<>();
        agentGetSocketsCallback.getSockets(outletDTOS, RespondCodes.OK);
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public void updateSocket(OutletDTO outletDTO) {

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

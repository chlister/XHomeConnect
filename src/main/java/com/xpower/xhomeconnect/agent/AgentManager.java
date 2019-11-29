/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.xhomeconnect.agent;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IAgentCallback;
import com.xpower.xhomeconnect.IAgentGetSocketsCallback;
import com.xpower.message.model.OutletDTO;

import java.util.ArrayList;
import java.util.List;

public class AgentManager implements IAgentManager {

    IAgentGetSocketsCallback mAgentGetSocketsCallback;
    IAgentCallback callback;
    List<OutletDTO> mOutlets;


    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public AgentManager(IAgentGetSocketsCallback agentGetSocketsCallback) {
        this.mAgentGetSocketsCallback = agentGetSocketsCallback;
    }

    public AgentManager(IAgentCallback callback) {
        mOutlets = new ArrayList<>();
        this.scanNetwork();
        this.callback = callback;

    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public List<OutletDTO> getSockets() {
        return mOutlets;
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public void updateSocket(OutletDTO outletDTO) {
        for (OutletDTO dto : mOutlets) {
            if (outletDTO.getAgentId() == dto.getAgentId() && outletDTO.getId() == dto.getId()) {
                dto.setApplianceType(outletDTO.getApplianceType());
                dto.setName(outletDTO.getName());
            }
        }
    }

    @Override
    public void changeState(OutletDTO outletDTO) {
        for (OutletDTO outlet: mOutlets) {
            if (outlet.getAgentId() == outletDTO.getAgentId() && outlet.getId() == outletDTO.getId()){
                System.out.println("Socket state used to be: " + outlet.getState());
                outlet.setState(outletDTO.getState());
                System.out.println("Socket state is now: " + outlet.getState());
            }
        }
    }
    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public List<String> scanNetwork() {
        mOutlets.add(
                new OutletDTO(1, 1, "", "", false)
        );
        mOutlets.add(
                new OutletDTO(2, 1, "", "", false)
        );
        mOutlets.add(
                new OutletDTO(1, 2, "", "", false)
        );
        mOutlets.add(
                new OutletDTO(2, 2, "", "", false)
        );
        mOutlets.add(
                new OutletDTO(1, 3, "", "", false)
        );

        return null;
    }
}

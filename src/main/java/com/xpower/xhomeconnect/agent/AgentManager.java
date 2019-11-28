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
    List<OutletDTO> mHomeSockets;


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
        mHomeSockets = new ArrayList<>();
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
    public void getSockets() {
        List<OutletDTO> outletDTOS = new ArrayList<>();
        mAgentGetSocketsCallback.getSockets(outletDTOS, RespondCodes.OK);
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

    @Override
    public void changeState(OutletDTO outletDTO) {
        // TODO: find the socket
        for (OutletDTO socket: mHomeSockets) {
            if (socket.getAgentId() == outletDTO.getAgentId() && socket.getId() == outletDTO.getId()){
                System.out.println("Socket state used to be: " + socket.getState());
                socket.setState(outletDTO.getState());
                System.out.println("Socket state is now: " + socket.getState());
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
        mHomeSockets.add(
                new OutletDTO(1, 1, "", "", false)
        );
        mHomeSockets.add(
                new OutletDTO(2, 1, "", "", false)
        );
        mHomeSockets.add(
                new OutletDTO(1, 2, "", "", false)
        );
        mHomeSockets.add(
                new OutletDTO(2, 2, "", "", false)
        );
        mHomeSockets.add(
                new OutletDTO(1, 3, "", "", false)
        );

        return null;
    }
}

/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.xhomeconnect.agent;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IAgentCallback;
import com.xpower.xhomeconnect.IAgentChangeStateCallBack;
import com.xpower.xhomeconnect.IAgentGetSocketsCallback;
import com.xpower.message.model.SocketDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AgentManager implements IAgentManager {

    IAgentGetSocketsCallback mAgentGetSocketsCallback;
    IAgentCallback callback;
    List<SocketDTO> mHomeSockets;


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
        List<SocketDTO> socketDTOS = new ArrayList<>();
        mAgentGetSocketsCallback.getSockets(socketDTOS, RespondCodes.OK);
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

    @Override
    public void changeState(SocketDTO socketDTO) {
        // TODO: find the socket
        for (SocketDTO socket: mHomeSockets) {
            if (socket.getAgentId() == socketDTO.getAgentId() && socket.getId() == socketDTO.getId()){
                System.out.println("Socket state used to be: " + socket.getState());
                socket.setState(socketDTO.getState());
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
                new SocketDTO(1, 1, "", "", false)
        );
        mHomeSockets.add(
                new SocketDTO(2, 1, "", "", false)
        );
        mHomeSockets.add(
                new SocketDTO(1, 2, "", "", false)
        );
        mHomeSockets.add(
                new SocketDTO(2, 2, "", "", false)
        );
        mHomeSockets.add(
                new SocketDTO(1, 3, "", "", false)
        );

        return null;
    }
}

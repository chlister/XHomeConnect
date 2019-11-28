/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.agent;

import com.xpower.message.model.OutletDTO;

import java.util.List;

public interface IAgentManager {
    void getSockets();
//    Agent getAgent(String agent); // TODO implement when Agent class is defined
    void updateSocket(OutletDTO outletDTO);
    List<String> scanNetwork();

}

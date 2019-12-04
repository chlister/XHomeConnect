/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.agent;

import com.xpower.message.model.OutletDTO;

import java.util.List;

public interface IAgentManager {
    List<OutletDTO> getOutlets();
    void updateOutlet(OutletDTO outletDTO);
    void changeState(OutletDTO outletDTO);
    List<String> scanNetwork();

}

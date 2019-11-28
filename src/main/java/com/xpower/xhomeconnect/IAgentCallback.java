package com.xpower.xhomeconnect;

import com.xpower.message.model.OutletDTO;

public interface IAgentCallback {
    void changeState(OutletDTO outletDTO);
}

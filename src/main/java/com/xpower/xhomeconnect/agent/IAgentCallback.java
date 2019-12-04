package com.xpower.xhomeconnect.agent;

import com.xpower.message.RespondCodes;
import com.xpower.message.model.OutletDTO;

import java.util.List;

public interface IAgentCallback {
    void outletChangedEvent(List<OutletDTO> mOutlets, RespondCodes respond);
}

package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.message.model.OutletDTO;

public interface IAgentChangeStateCallBack {
    void changeState(OutletDTO outletDTO, RespondCodes respondCodes);
}

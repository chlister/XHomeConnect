package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.message.model.OutletDTO;

import java.util.List;

public interface IAgentCallback {
    void changeState(OutletDTO outletDTO);
    void getSockets(List<OutletDTO> sockets, RespondCodes respondCodes);

}

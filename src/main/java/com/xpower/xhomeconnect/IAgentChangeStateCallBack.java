package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.message.model.SocketDTO;

public interface IAgentChangeStateCallBack {
    void changeState(SocketDTO socketDTO, RespondCodes respondCodes);
}

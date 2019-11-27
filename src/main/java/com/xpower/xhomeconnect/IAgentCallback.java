package com.xpower.xhomeconnect;

import com.xpower.message.model.SocketDTO;

public interface IAgentCallback {
    void changeState(SocketDTO socketDTO);
}

/**
 * TODO: Add interface description
 *
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.message.model.SocketDTO;

import java.util.List;

public interface IAgentGetSocketsCallback {
    void getSockets(List<SocketDTO> sockets, RespondCodes respondCodes);
}
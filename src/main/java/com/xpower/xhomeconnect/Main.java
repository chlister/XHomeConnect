/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/21/19
 */

package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.agent.AgentManager;
import com.xpower.xhomeconnect.websocket.WebSocketManager;

public class Main {

    /**
     * @param args - arguments
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    public static void main(String[] args) {
        HomeController hm = new HomeController();
        hm.setManagers(new WebSocketManager(hm), new AgentManager(hm));
        hm.init();

    }
}

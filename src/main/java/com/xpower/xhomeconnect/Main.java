/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/21/19
 */

package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.agent.AgentManager;
import com.xpower.xhomeconnect.agent.MockAgentManager;
import com.xpower.xhomeconnect.websocket.WebSocketManager;

public class Main {

    /**
     * @param args - arguments
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    public static void main(String[] args) {
        // Check if test
        HomeController hm = new HomeController();

        if (args.length > 0 && args[0].equals("test")) {
        hm.setManagers(new WebSocketManager(hm), new MockAgentManager(hm));

        } else
                hm.setManagers(new WebSocketManager(hm), new AgentManager(hm));
        hm.init();

    }
}

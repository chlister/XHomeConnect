/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */
package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.agent.AgentManager;
import com.xpower.xhomeconnect.agent.IAgentManager;
import com.xpower.message.model.OutletDTO;
import com.xpower.xhomeconnect.websocket.WebSocketManager;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketEngine;

import java.io.IOException;
import java.util.List;

/**
 * Main class of the XHomeConnect module.
 */
public class HomeController implements IWebSocketCallback, IAgentCallback {
    IAgentManager mAgentManager;
    WebSocketManager mWebSocketManager;
//    IApiClientManager mApiClientManager; - TODO class not defined

    public HomeController() {
        mAgentManager = new AgentManager(this);
    }

    /**
     * Initialises the HTTP server - which includes the end point for the websocket
     *
     * @author Marc R. K.
     * @version 0.3
     * @status Under Development
     * @since 11/20/19
     */
    public void init() {

        // Create a simple webserver. root of server is the first param - HTML files should be put here.
        HttpServer server =
                HttpServer.createSimpleServer("src/main/java/com/xpower/xhomeconnect/websocket", 80);

        // Adding ws: functionality to the webserver
        WebSocketAddOn addon = new WebSocketAddOn();
        server.getListeners().forEach(x -> {
            x.registerAddOn(addon);
        });
        // This is the class responsible for handling WebSocket events.
        mWebSocketManager = new WebSocketManager(this);

        // ws://localhost:80/x/home - url for the websocket.
        WebSocketEngine.getEngine().register("/x", "/home", mWebSocketManager);

        // When application closes - we close the server gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Stopping server...");
                server.shutdownNow();
                System.out.println("Stopped server");
            }
        }, "shutdownHook"));

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Started server");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to return a list of socketDTOs
     *
     * @author Marc R. K.
     * @version 0.3
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void getSockets(WebSocket socket) {
        List<OutletDTO> outlets = mAgentManager.getSockets();
        if (outlets.isEmpty())
            mWebSocketManager.returnSockets(socket, RespondCodes.NOT_FOUND, outlets);
        else
            mWebSocketManager.returnSockets(socket, RespondCodes.OK, outlets);
    }

    /**
     * Used to register a specific socket.
     *
     * @author Marc R. K.
     * @version 0.1
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void registerOutlet(OutletDTO outletDTO) {
        mAgentManager.updateOutlet(outletDTO);
    }

    @Override
    public void changeState(OutletDTO outletDTO) {
        mAgentManager.changeState(outletDTO);

    }

    @Override
    public void outletChangedEvent(List<OutletDTO> outlets, RespondCodes response) {
        mWebSocketManager.outletChangedEvent(outlets, response);
    }


}




/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */
package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.agent.AgentManager;
import com.xpower.xhomeconnect.agent.IAgentManager;
import com.xpower.message.model.SocketDTO;
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
public class HomeController implements IWebSocketCallback {
    IAgentManager mAgentManager; // TODO class not defined
    WebSocketManager mWebSocketManager;
//    IApiClientManager mApiClientManager; - TODO class not defined


    /**
     * Initialises the HTTP server - which controls the end point for the websocket
     *
     * @author Marc R. K.
     * @version 0.3
     * @status Under Development
     * @since 11/20/19
     */
    public void init() {
        // Create a server, first param docroot (only used if .html files are in the project) therefore using port which isn't HTTP
        HttpServer server =
                HttpServer.createSimpleServer("src/main/java/com/xpower/xhomeconnect/websocket", 20860);
        WebSocketAddOn addon = new WebSocketAddOn();

        server.getListeners().forEach(x -> {
            x.registerAddOn(addon);
        });

        // Defining which class handles the websocket part
        mWebSocketManager = new WebSocketManager(this);

        // ws://localhost:20860/x/home - url for the websocket.
        WebSocketEngine.getEngine().register("/x", "/home", mWebSocketManager);

        // register shutdown hook
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
         mAgentManager = new AgentManager(new IAgentGetSocketsCallback() {
             @Override
             public void getSockets(List<SocketDTO> sockets, RespondCodes code) {
                mWebSocketManager.returnSockets(socket, code, sockets);
             }
         });
         mAgentManager.getSockets();
    }

    /**
     * Used to search local net for units matching the Netio agent signature.
     *
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public void detectLocalAgents() {
        mAgentManager.scanNetwork();
    }

    /**
     * Used to register a specific socket.
     * @author Marc R. K.
     * @version 0.1
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public void registerSocket(SocketDTO socketDTO) {
        mAgentManager.updateSocket(socketDTO);
    }
}




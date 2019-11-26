/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */
package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.SocketDTO;
import com.xpower.xhomeconnect.websocket.WebSocketManager;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketEngine;

import java.io.IOException;
import java.util.List;

/**
 * Main class of the XHomeConnect module.
 */
public class HomeController implements IWebSocketCallback {
    IAgentManager mAgentManager; // TODO class not defined
    WebSocketApplication mWebSocketManager;
//    IApiClientManager mApiClientManager; - TODO class not defined

    /**
     * Initialises the HTTP server - which controls the end point for the websocket
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
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
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public List<SocketDTO> getSockets() {
        return mAgentManager.getSockets();
    }

    /**
     * Used to search local net for units matching the Netio agent signature.
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     */
    @Override
    public void detectLocalAgents() {
        mAgentManager.scanNetwork();
    }

    /**
     * Used to register a specific socket.
     * @author  Marc R. K.
     * @version 0.1
     * @since   11/20/19
     * @status  Defined
     * @return HttpStatus - returns whether the request is successful
     */
    @Override
public HttpStatus registerSocket(SocketDTO socketDTO) {
        mAgentManager.updateSocket(socketDTO);
        return HttpStatus.OK_200;
    }
}




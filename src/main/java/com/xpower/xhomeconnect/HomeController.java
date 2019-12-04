/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */
package com.xpower.xhomeconnect;

import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.agent.IAgentCallback;
import com.xpower.xhomeconnect.agent.IAgentManager;
import com.xpower.message.model.OutletDTO;
import com.xpower.xhomeconnect.websocket.IWebSocketCallback;
import com.xpower.xhomeconnect.websocket.IWebSocketManager;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketAddOn;

import java.io.IOException;
import java.util.List;

public class HomeController implements IWebSocketCallback, IAgentCallback {
    private IAgentManager mAgentManager;
    private IWebSocketManager mWebSocketManager;

    /**
     * Injects dependencies
     *
     * @param webSocketManager IWebSocketManager
     * @param agentManager     IAgentManager
     * @author Marc R. K.
     * @status Done
     * @since 12/01/19
     */
    public void setManagers(IWebSocketManager webSocketManager, IAgentManager agentManager) {
        mAgentManager = agentManager;
        mWebSocketManager = webSocketManager;
    }

    /**
     * Initialises the HTTP server - which includes the end point for the websocket
     *
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    public void init() {

        // If managers haven't been initialised then throw nullpointer
        if (mAgentManager == null || mWebSocketManager == null)
            throw new NullPointerException();
            // Create a simple webserver. root of server is the first param - HTML files should be put here.
            HttpServer server =
                    HttpServer.createSimpleServer("src/main/java/com/xpower/xhomeconnect/websocket", 80);

            // Adding websocket functionality to the webserver
            WebSocketAddOn addon = new WebSocketAddOn();
            server.getListeners().forEach(x -> {
                x.registerAddOn(addon);
            });

            // Websocket endpoint
            mWebSocketManager.registerSocketConnection("/x", "/home");

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
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     * @param socket WebSocket
     */
    @Override
    public void onGetOutletRequest(WebSocket socket) {
        List<OutletDTO> outlets = mAgentManager.getOutlets();
        if (outlets.isEmpty())
            mWebSocketManager.returnOutlets(socket, RespondCodes.NOT_FOUND, outlets);
        else
            mWebSocketManager.returnOutlets(socket, RespondCodes.OK, outlets);
    }

    /**
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    @Override
    public void onRegisterOutletRequest(OutletDTO outletDTO) {
        mAgentManager.updateOutlet(outletDTO);
    }

    /**
     * @author Marc R. K.
     * @status Done
     * @since 11/27/19
     */
    @Override
    public void onChangeStateRequest(OutletDTO outletDTO) {
        mAgentManager.changeState(outletDTO);

    }

    /**
     * @author Marc R. K.
     * @status Done
     * @since 11/27/19
     */
    @Override
    public void outletChangedEvent(List<OutletDTO> outlets, RespondCodes response) {
        mWebSocketManager.outletChangedEvent(outlets, response);
    }


}




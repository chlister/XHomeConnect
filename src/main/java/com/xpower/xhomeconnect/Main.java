/**
 * TODO: Add class description
 *
 * @author Marc R. K.
 * @version 1.0
 * @since 11/21/19
 */

package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.XHomeApplication;
import org.glassfish.grizzly.http.Protocol;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.websockets.*;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    /**
     * Main method. handles start of controller class
     * @param args
     */
    public static void main(String[] args) {

        HttpServer server = HttpServer.createSimpleServer("src/main/webapp", 20860);
        WebSocketAddOn addon = new WebSocketAddOn();

        server.getListeners().forEach(x -> {
            x.registerAddOn(addon);
        });

        WebSocketApplication websocketApp = new XHomeApplication();

        // ws://localhost:20860/api/home
        WebSocketEngine.getEngine().register("/api", "/home", websocketApp);

        // register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Stopping server...");
                server.shutdownNow();
                System.out.println("Stopped server");
            }
        }, "shutdownHook"));

        server.start();
        System.out.println("Started server");

        Thread.currentThread().join();
    }
}

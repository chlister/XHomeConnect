/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/21/19
 */

package com.xpower.xhomeconnect;

import com.xpower.xhomeconnect.websocket.XHomeApplication;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.websockets.*;

import java.io.IOException;

public class Main {

    /**
     * Main class initialises the Webserver.
     * @param args - arguments
     * @author Marc R. K.
     * @version 0.3
     * @status Defined
     * @since 11/20/19
     */
    public static void main(String[] args) {

        // Create a server, first param docroot (only used if .html files are in the project) therefore using port which isn't HTTP
        HttpServer server =
                HttpServer.createSimpleServer("src/main/java/com/xpower/xhomeconnect/websocket", 20860);
        WebSocketAddOn addon = new WebSocketAddOn();

        server.getListeners().forEach(x -> {
            x.registerAddOn(addon);
        });

        // Defining which class handles the websocket part
        WebSocketApplication websocketApp = new XHomeApplication();

        // ws://localhost:20860/api/home - url for the websocket.
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
}

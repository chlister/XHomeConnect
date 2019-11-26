/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/21/19
 */

package com.xpower.xhomeconnect;

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
        HomeController hm = new HomeController();
        hm.init();

    }
}

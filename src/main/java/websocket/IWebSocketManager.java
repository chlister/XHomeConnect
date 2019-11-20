/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package websocket;

public interface IWebSocketManager {

    /**
     * listener event when websocket opens
     */
    void onOpen();

    /**
     * listener event when the connection closes (failure or otherwise)
     */
    void onClose();

    /**
     * When the client sends a message to the websocket
     */
    void onMessage();

    /**
     * When a socket needs to be updated
     * @param socket SocketDTO
     */
    void updateSocket(SocketDTO socket);
}

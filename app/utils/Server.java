package server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static boolean listeningSocket;
    static ServerSocket serverSocket = null;

    public static void listen(int port) throws IOException {

        try {
            serverSocket = new ServerSocket(port);
            listeningSocket = true;
        } catch (IOException e) {
            System.err.println("Could not listen on port: 9021");
        }

        serverSocket.close();       
    }

    public static boolean isListeningSocket(){
        return listeningSocket;
    }

    public static void setListeningSocket(boolean listening){
        listeningSocket = listening;
    }

    public static ServerSocket getServerSocket(){
        return ServerSocket;
    }

}

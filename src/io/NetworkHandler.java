
package io;

import java.net.ServerSocket;
import java.net.Socket;

public class NetworkHandler implements Runnable {
    
    private ServerSocket server;
    Socket socket;
    private boolean client;
    int port;
    String ip;
    
    public NetworkHandler(){
        client = false;
    }
    
    public NetworkHandler(String port, int ip){
        
    }
    
    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    @Override
    public void run() {
        if (client){
            connectToServer();
        }
    }

    private void connectToServer() {
        socket = new Socket();
    }
    
}

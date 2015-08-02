
package io;

import gui.Board;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

public class NetworkHandler {
    
    private ServerSocket server;
    private boolean client;
    private Board board;
    private InputStream in;
    private DataInputStream dataIn;
    private OutputStream out;
    private DataOutputStream dataOut;
    private Socket socket;
    
    private static Logger logger = Logger.getLogger(NetworkHandler.class);
    
    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip,port);
            //-- setup data input and output stream after succesfull onnection --
            in = socket.getInputStream();
            dataIn = new DataInputStream(in);
            out = socket.getOutputStream();
            dataOut = new DataOutputStream(out);
            // ------------------------------
            client = true;      // if this application tries to connect a server then this is the client
            return true;
        } catch (IOException ex) {
            logger.error("Cannot connect to server",ex);
            return false;
        }
    }
    
    public synchronized int startServer(){
        try {
            server = new ServerSocket(0);
            return server.getLocalPort();
        } catch (IOException ex) {
            logger.error("cannot start server",ex);
            return -1;
        }
    }
    
    public synchronized void acceptPlayer(){
        /* after starting a server this will be called. It will wait until another player connects. Then this becomes the host */
        try {
            socket = server.accept();
            // -- setup data input and output streams after successfull connection --
            in = socket.getInputStream();
            dataIn = new DataInputStream(in);
            out = socket.getOutputStream();
            dataOut = new DataOutputStream(out);
            // -------------------------------
            client = false;
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
    
    public synchronized int recieveMove(){  // listen until other player send his move over network
        try {
            return dataIn.readInt();
        } catch (IOException ex) {
            logger.error(ex);
            return -1;
        }
    }
    
    public void sendMove(int move){     // send my move to other player over network
        try {
            dataOut.writeInt(move);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
}

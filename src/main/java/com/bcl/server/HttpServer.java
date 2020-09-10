package com.bcl.server;

import com.bcl.controllers.get.index;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * @author Sam Salfi
 * Creates a simple HTTP server
 */
public class HttpServer {
    private ServerSocket serverSocket;
    private Boolean listening = true;

    public HttpServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not create HTTP server");
            e.printStackTrace();
        }
        //this.startListening();
    }

    public void startListening() {
        listening = true;
        System.out.println("Server is listening on port " + serverSocket.getLocalPort());
        while (listening) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                RequestMessage req = new RequestMessage(socket.getInputStream());
                ResponseMessage resp = new ResponseMessage(socket);
                new index().GET(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

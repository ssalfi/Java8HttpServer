package com.bcl.server;

import com.bcl.controllers.index;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Hashtable;

/**
 * @author Sam Salfi
 * Creates a simple HTTP server
 */
public class HttpServer {
    private ServerSocket serverSocket;
    private Boolean listening = true;
    private Hashtable<String, RequestHandler> RequestPaths;

    public HttpServer(int port) {
        RequestPaths = new Hashtable<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not create HTTP server");
            e.printStackTrace();
        }
        //this.startListening();
    }

    /**
     * Specify a RequestHandler to handle a specific request at a given path
     * @param path The path to handle
     * @param handler The class that handles the request
     */
    public void handlePath(String path, RequestHandler handler) {
        RequestPaths.put(path, handler);
    }

    /**
     * Used by the server to handle a request
     * @param req
     * @param resp
     */
    private void handleRequest(RequestMessage req, ResponseMessage resp) {
        String path = req.getPath();

        if (RequestPaths.containsKey(path)) {
            if (req.getMethod().equals("GET")) {
                RequestPaths.get(path).GET(req, resp);
            } else if (req.getMethod().equals("POST")) {
                RequestPaths.get(path).POST(req, resp);
            } else {
                resp.sendText(405, "Method is not supported");
            }
        } else {
            resp.sendText(404, "File not found");
        }
    }

    /**
     * Start listening to the port
     */
    public void startListening() {
        listening = true;
        System.out.println("Server is listening on port " + serverSocket.getLocalPort());
        while (listening) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                RequestMessage req = new RequestMessage(socket.getInputStream());
                ResponseMessage resp = new ResponseMessage(socket);
                handleRequest(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

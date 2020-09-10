package com.bcl.app;
import com.bcl.server.HttpServer;
import com.bcl.controllers.*;
public class App {
    public static void main(String[] args)  {
        HttpServer server = new HttpServer(80);



        server.startListening();
    }
}
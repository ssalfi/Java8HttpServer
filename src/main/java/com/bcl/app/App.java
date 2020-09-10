package com.bcl.app;
import com.bcl.server.HttpServer;
import com.bcl.controllers.*;

import java.io.File;

public class App {
    public static void main(String[] args)  {
        HttpServer server = new HttpServer(80);

        server.handlePathWithDirectory("/", new File("public"), true);
        server.handlePath("/parameters/test", new com.bcl.controllers.parameters.test());

        server.startListening();
    }
}
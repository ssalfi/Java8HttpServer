package com.bcl.app;

import com.bcl.genericHandlers.GETTextHandler;
import com.bcl.server.HttpServer;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppTest {
    @Test
    public void ServerTest() {
        HttpServer server = new HttpServer(80);
        server.handlePath("/", new GETTextHandler("Hello World!"));
        server.startListening();

        try {
            Socket s = new Socket("localhost", 80);
            PrintWriter writeOut = new PrintWriter(s.getOutputStream());
            writeOut.print("GET / HTTP/1.1\r\n");
            writeOut.print("Host: localhost\r\n\r\n");
            writeOut.flush();

            Scanner scanner = new Scanner(s.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();

            writeOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
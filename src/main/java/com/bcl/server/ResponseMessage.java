package com.bcl.server;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseMessage {
    private SimpleDateFormat date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z"); // Used for adding time to the headers

    private PrintWriter outputPrintWriter;
    private Socket socket;

    public ResponseMessage(Socket os) {
        socket = os;
        try {
            outputPrintWriter = new PrintWriter(os.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeaders(int statusCode, String contentType) {
        outputPrintWriter.print("HTTP/1.1 "+statusCode+" OK\r\n");
        outputPrintWriter.print("Date: " + date.format(new Date()) + "\r\n");
        outputPrintWriter.print("Content-Type: " + contentType + "; charset=utf-8" + "\r\n");
        //outputPrintWriter.print("Content-Length: " + data.length() + "\r\n\r\n");
        outputPrintWriter.print("Transfer-Encoding: chunked\r\n\r\n");
    }

    public void sendText(int statusCode, String data) {
        this.writeHeaders(statusCode, "text/plain");
        outputPrintWriter.print(Integer.toHexString(data.length()) + "\r\n");
        outputPrintWriter.print(data + "\r\n");
        outputPrintWriter.print("0\r\n\r\n");
        outputPrintWriter.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void send(int statusCode) {
        this.writeHeaders(statusCode, "text/plain");
        outputPrintWriter.print("0\r\n\r\n");
        outputPrintWriter.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

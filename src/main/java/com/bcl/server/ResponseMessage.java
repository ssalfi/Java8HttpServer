package com.bcl.server;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Handles sending responses to clients
 */
public class ResponseMessage {
    private SimpleDateFormat date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z"); // Used for adding time to the headers
    private PrintWriter outputPrintWriter; // PrintWriter for writing data to the socket output
    private Socket socket;

    public ResponseMessage(Socket os) {
        socket = os;
        try {
            outputPrintWriter = new PrintWriter(os.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the headers to the response
     * @param statusCode The status code of the response (E.G. 200, 404)
     * @param contentType The content type of the response (E.G. text/html, text/css)
     */
    private void writeHeaders(int statusCode, String contentType) {
        outputPrintWriter.print("HTTP/1.1 "+statusCode+" OK\r\n");
        outputPrintWriter.print("Date: " + date.format(new Date()) + "\r\n");
        outputPrintWriter.print("Content-Type: " + contentType + "; charset=utf-8" + "\r\n");
        //outputPrintWriter.print("Content-Length: " + data.length() + "\r\n\r\n");
        outputPrintWriter.print("Transfer-Encoding: chunked\r\n\r\n");
    }

    /**
     * Write a single line to the socket
     * @param line The line to write
     */
    private void writeLineToSocket(String line) {
        outputPrintWriter.print(Integer.toHexString(line.length()) + "\r\n");
        outputPrintWriter.print(line + "\r\n");
    }

    /**
     * Write the final line, and close the socket, sending the data
     */
    private void closeSocket() {
        outputPrintWriter.print("0\r\n\r\n");
        outputPrintWriter.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send an image file to the client
     * @param image The image file to send
     * @param mime The mime type of the image
     */
    public void sendImageFile(File image, String mime) {
        this.writeHeaders(200, mime);
        try {
            ImageIO.write(ImageIO.read(image), mime.split("/")[1], socket.getOutputStream() );
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send plain text to the client
     * @param statusCode The status code of the response
     * @param text The text to send
     */
    public void sendText(int statusCode, String text) {
        this.writeHeaders(statusCode, "text/plain");
        writeLineToSocket(text);
        this.closeSocket();
    }


    /**
     * Sends a file and tries to set the ContentType automatically
     * @param fileToSend The file to send
     */
    public void sendFile(File fileToSend) {
        try {
            String mime = MimeTypeGetter.get(fileToSend);
            String mimeType = mime.split("/")[0];
            switch(mimeType) {
                case "application":
                case "text":
                    sendTextFile(fileToSend, mime);
                    break;
                case "image":
                    sendImageFile(fileToSend, mime);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a file of sub-content-type "text" (e.g. text/css, text/html);
     * @param fileToSend The file to send
     * @param contentType The Text/ContentType to send (e.g. "text/css", "text/html");
     */
    public void sendTextFile(File fileToSend, String contentType) {
        if (fileToSend.canRead() && fileToSend.exists()) {
            try {
                Scanner scanner = null;
                scanner = new Scanner(fileToSend);

                this.writeHeaders(200, contentType);
                while (scanner.hasNextLine()) {
                    writeLineToSocket(scanner.nextLine());
                }
                scanner.close();
                this.closeSocket();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                sendText(404, "File not found");
            }
        } else {
            System.out.println("The file " + fileToSend.getAbsolutePath() + " either does not exist or cannot be read");
            sendText(404, "File not found");
        }
    }

    /**
     * Send a simple response back
     * @param statusCode The status code of the response
     */
    public void send(int statusCode) {
        this.writeHeaders(statusCode, "text/plain");
        this.closeSocket();
    }

}

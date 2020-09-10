package com.bcl.genericControllers;

import com.bcl.server.RequestHandler;
import com.bcl.server.RequestMessage;
import com.bcl.server.ResponseMessage;

import java.io.File;

/**
 * Handles the given file
 */
public class getFileHandler implements RequestHandler {
    File fileToSend;

    public getFileHandler(File fileToSend) {
        this.fileToSend = fileToSend;
    }

    public void GET(RequestMessage req, ResponseMessage resp) {
        resp.sendFile(fileToSend);
    }
}

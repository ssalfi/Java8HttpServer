package com.bcl.genericHandlers;

import com.bcl.server.RequestHandler;
import com.bcl.server.RequestMessage;
import com.bcl.server.ResponseMessage;

import java.io.File;

/**
 * Handles the given file
 */
public class GETTextHandler implements RequestHandler {
    String text;

    public GETTextHandler(String text) {
        this.text = text;
    }

    public void GET(RequestMessage req, ResponseMessage resp) {
        resp.sendText(200, text);
    }
}

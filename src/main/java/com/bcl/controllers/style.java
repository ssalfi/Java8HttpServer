package com.bcl.controllers;

import com.bcl.server.RequestHandler;
import com.bcl.server.RequestMessage;
import com.bcl.server.ResponseMessage;

import java.io.File;

public class style implements RequestHandler {
    public void GET(RequestMessage req, ResponseMessage resp) {
        resp.sendFile(new File("public/index.css"));
    }

}

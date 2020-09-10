package com.bcl.controllers;

import com.bcl.server.RequestMessage;
import com.bcl.server.RequestHandler;
import com.bcl.server.ResponseMessage;

public class index implements RequestHandler {
    public void GET(RequestMessage req, ResponseMessage resp) {
        resp.sendHtml("public/index.html");
    }

}

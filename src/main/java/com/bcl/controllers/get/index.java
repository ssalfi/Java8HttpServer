package com.bcl.controllers.get;

import com.bcl.server.RequestMessage;
import com.bcl.server.RequestHandler;
import com.bcl.server.ResponseMessage;

import java.util.Map;

public class index implements RequestHandler {
    public void GET(RequestMessage req, ResponseMessage resp) {
        String out = "Your parameters:\n";

        for (Map.Entry<String, String> set : req.getParameters().entrySet()) {
            out += set.getKey() + " = " + set.getValue() + "\n";
        }

        resp.sendText(200, out);
    }
    public void POST(RequestMessage req, ResponseMessage resp) {
        resp.send(404);
    }

}

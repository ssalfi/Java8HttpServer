package com.bcl.controllers.parameters;

import com.bcl.server.RequestHandler;
import com.bcl.server.RequestMessage;
import com.bcl.server.ResponseMessage;

import java.util.Map;

public class test implements RequestHandler {
    public void GET(RequestMessage req, ResponseMessage resp) {
        String out = "Your parameters:\n";

        for (Map.Entry<String, String> set : req.getParameters().entrySet()) {
            out += set.getKey() + " = " + set.getValue() + "\n";
        }

        resp.sendText(200, out);
    }

}

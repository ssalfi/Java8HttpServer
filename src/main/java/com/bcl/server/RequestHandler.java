package com.bcl.server;

public interface RequestHandler {
    public void GET(RequestMessage req, ResponseMessage resp);
    public void POST(RequestMessage req, ResponseMessage resp);
}

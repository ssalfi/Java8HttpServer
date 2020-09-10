package com.bcl.server;

public interface RequestHandler {
    default void GET(RequestMessage req, ResponseMessage resp) {
        resp.send(404);
    }
    default void POST(RequestMessage req, ResponseMessage resp) {
        resp.send(404);
    }
}

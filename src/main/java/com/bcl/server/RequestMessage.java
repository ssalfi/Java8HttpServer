package com.bcl.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Stores information about a HTTP request
 */
public class RequestMessage {
    private String Method;
    private String ProtocolVersion;
    private String Path;

    private Hashtable<String, String> Parameters; // Holds all the parameters of the request
    private final Hashtable<String, String> Headers; // Holds all the headers of the request

    /**
     * Parses an InputStream HTTP request into a HashTable
     * @param stream An InputStream of headers
     */
    public RequestMessage(InputStream stream) {
        Headers = new Hashtable<>();
        Parameters = new Hashtable<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;

        try {
            String[] split = in.readLine().split(" "); // Split up the first line of the request (e.g GET / HTTP/1.1)

            this.Method = split[0]; // "GET"
            this.Path = split[1]; // "/"
            this.ProtocolVersion = split[2]; // "HTTP/1.1"

            while (!(inputLine = in.readLine()).equals("")) {// Go through the rest of the headers
                split = inputLine.split(": ");
                this.Headers.put(split[0], split[1]);
            }

            this.parseParameters();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse all of the parameters, and get rid of the parameters from the path variable
     */
    private void parseParameters() {
        if (Path.contains("?")) {
            String params = getPath().substring(Path.indexOf("?") + 1);
            Path = Path.replace("?"+params, ""); // Cut out the parameters from the path

            String[] splitNameValPair = params.split("&");
            for (String nameValPair : splitNameValPair) {
                String[] nameVal = nameValPair.split("=");
                if (nameVal.length >= 1) {
                    Parameters.put(nameVal[0], nameVal.length == 2 ? nameVal[1] : "");
                }
            }
        }

    }

    //Getters
    public Hashtable<String, String> getHeaders() { return Headers; }
    public Hashtable<String, String> getParameters() { return Parameters; }
    public String getMethod() { return Method; }
    public String getProtocolVersion() { return ProtocolVersion; }
    public String getPath() { return Path; }
}

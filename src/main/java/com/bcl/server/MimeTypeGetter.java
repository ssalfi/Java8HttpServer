package com.bcl.server;

import java.io.File;
import java.io.InvalidObjectException;

public class MimeTypeGetter {
    public static String get(File file) throws InvalidObjectException {
        String[] split = file.getName().split("\\.");
        String fileExtension = split.length > 1 ? split[split.length-1].toLowerCase() : null;

        if (fileExtension.length() == 0) {
            throw new InvalidObjectException("File has no extension");
        }

        switch(fileExtension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "js":
                return "application/javascript";
            case "json":
                return "application/json";
            case "css":
                return "text/css";
            case "ico":
                return "image/vnd.microsoft.icon";
            case "htm":
            case "html":
                return "text/html";
        }

        throw new InvalidObjectException("Could not find mime type for " + file.getName());
    }
}

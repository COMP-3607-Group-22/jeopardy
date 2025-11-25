package com.project.Helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHelper {
    public String getTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss','");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(formatter);
    }
}

package com.project.Helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Small utility for producing timestamp strings used in log entries.
 */
public class LogHelper {
    /**
     * Return a formatted timestamp string used in event log entries.
     *
     * @return timestamp in the pattern yyyy-MM-dd'T'HH:mm:ss,
     */
    public String getTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss','");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(formatter);
    }
}

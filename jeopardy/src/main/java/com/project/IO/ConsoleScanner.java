package com.project.IO;

import java.util.Scanner;

/**
 * Simple ConsoleIO implementation that uses `java.util.Scanner` to read user
 * input from standard in and writes to standard out.
 */
public class ConsoleScanner implements ConsoleIO {
    private final Scanner scanner;

    /**
     * Create a ConsoleScanner that reads from standard input.
     */
    public ConsoleScanner() {
        this.scanner = new Scanner(System.in);
    }

    /** 
     * @return String
     */
    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Print a message followed by a newline to standard output.
     *
     * @param message the message to print (may be null)
     */
    @Override
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Print a message to standard output without adding a newline.
     *
     * @param message the message to print (may be null)
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Close the underlying scanner. Call this when the application is
     * shutting down to release system resources.
     */
    @Override
    public void close() {
        scanner.close();
    }
}

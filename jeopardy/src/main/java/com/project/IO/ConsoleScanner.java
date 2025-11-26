package com.project.IO;

import java.util.Scanner;

/**
 * Simple ConsoleIO implementation that uses `java.util.Scanner` to read user
 * input from standard in and writes to standard out.
 */
public class ConsoleScanner implements ConsoleIO {
    private final Scanner scanner;

    public ConsoleScanner() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Close the underlying scanner. Call this when the application is
     * shutting down to release system resources.
     */
    public void close() {
        scanner.close();
    }
}

package com.project.IO;

/**
 * Abstraction for console-based input/output used by the application.
 * Allows swapping ConsoleScanner for other I/O implementations in tests.
 */
public interface ConsoleIO {
    /**
     * Read a single line of input from the console.
     *
     * @return the input line as a String
     */
    String readLine();

    /**
     * Print a line terminated with a newline to the console.
     *
     * @param message the text to print
     */
    void println(String message);

    /**
     * Print text to the console without adding a terminating newline.
     *
     * @param message the text to print
     */
    void print(String message);
}

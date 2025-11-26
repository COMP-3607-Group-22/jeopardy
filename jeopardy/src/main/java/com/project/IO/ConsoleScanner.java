package com.project.IO;

import java.util.Scanner;

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
}

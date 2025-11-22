package com.project;
import java.util.ArrayList;

// Adapter Pattern - Target Interface
public interface FileParser {
    ArrayList<Question> parse(String fileName);
}

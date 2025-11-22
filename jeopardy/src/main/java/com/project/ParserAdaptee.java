package com.project;

import java.util.ArrayList;

// Adapter Pattern - Adaptee
public interface ParserAdaptee {
    ArrayList<Question> parse(String fileName);
}

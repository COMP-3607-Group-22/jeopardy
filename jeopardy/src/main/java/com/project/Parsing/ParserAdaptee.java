package com.project.Parsing;

import java.util.ArrayList;

import com.project.Categories.Question;

// Adapter Pattern - Adaptee
public interface ParserAdaptee {
    ArrayList<Question> parse(String fileName);
}

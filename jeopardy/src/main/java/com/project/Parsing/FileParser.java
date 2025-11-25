package com.project.Parsing;
import java.util.ArrayList;

import com.project.Questions.Question;

// Adapter Pattern - Target Interface
public interface FileParser {
    ArrayList<Question> parse(String fileName);
}

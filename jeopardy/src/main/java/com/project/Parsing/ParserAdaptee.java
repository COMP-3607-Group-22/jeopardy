package com.project.Parsing;

import java.util.ArrayList;

import com.project.Questions.Question;

// Adapter Pattern - Adaptee
/**
 * Low-level parser interface for a specific file format. Concrete parsers
 * (CSV/JSON/XML) implement this to provide format-specific parsing.
 */
public interface ParserAdaptee {
    /**
     * Parse the provided file and return a list of Questions.
     * Implementations should handle format-specific parsing and return
     * an empty list when no valid questions are found.
     *
     * @param fileName path to the input file (may be a classpath resource path)
     * @return list of parsed Question instances (never null)
     */
    ArrayList<Question> parse(String fileName);
}

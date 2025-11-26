package com.project.Parsing;
import java.util.ArrayList;

import com.project.Questions.Question;

/**
 * Public parsing interface used by the application. Implementations adapt
 * lower-level parsers and provide a single `parse` entrypoint.
 */
// Adapter Pattern - Target Interface
public interface FileParser {
    /**
     * Parse questions from the given file resource. The implementation may
     * adapt underlying format-specific parsers and should return an empty
     * list if no questions could be parsed.
     *
     * @param fileName path or resource name identifying the input file
     * @return list of parsed Question objects (never null)
     */
    ArrayList<Question> parse(String fileName);
}

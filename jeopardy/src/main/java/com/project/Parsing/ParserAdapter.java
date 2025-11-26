package com.project.Parsing;

import java.util.ArrayList;

import com.project.Questions.Question;

/**
 * Adapter that selects the appropriate concrete parser implementation based
 * on the file extension and exposes a uniform `FileParser` interface.
 */
// Adapter Pattern - Adapter
public class ParserAdapter implements FileParser {
    private ParserAdaptee parser;

    public ParserAdapter(String fileName) {
        String newFileName = "jeopardy/src/main/resources/" + fileName;
        if (newFileName == null || newFileName.isEmpty()) {
            throw new IllegalArgumentException("fileName is null or empty");
        }
        int index = newFileName.lastIndexOf('.');
        if (index < 0 || index == newFileName.length() - 1) {
            throw new IllegalArgumentException("fileName has no extension: " + fileName);
        }
        selectParserByFormat(newFileName.substring(index + 1).toLowerCase());
    }

    @Override
    public ArrayList<Question> parse(String fileName) {
        /**
         * Delegate parsing to the selected format-specific parser.
         *
         * @param fileName path to the input file
         * @return parsed questions
         * @throws IllegalStateException when no parser was selected
         */
        if (parser == null) {
            throw new IllegalStateException("No parser initialized");
        }
        return parser.parse(fileName);
    }

    private void selectParserByFormat(String format) {
        /**
         * Select a concrete parser implementation based on the extension
         * (csv/json/xml). Throws IllegalArgumentException for unsupported
         * formats.
         */
        switch (format.toLowerCase()) {
            case "csv":
                this.parser = new CSVParserAdaptee();
                break;
            case "json":
                this.parser = new JSONParserAdaptee();
                break;
            case "xml":
                this.parser = new XMLParserAdaptee();
                break;
            default:
                throw new IllegalArgumentException("Unsupported file format: " + format);
        }
    }
}

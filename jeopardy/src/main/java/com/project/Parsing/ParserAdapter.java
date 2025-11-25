package com.project.Parsing;

import java.util.ArrayList;

import com.project.Categories.Question;

// Adapter Pattern - Adapter
public class ParserAdapter implements FileParser {
    private ParserAdaptee parser;

    public ParserAdapter(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("fileName is null or empty");
        }
        int index = fileName.lastIndexOf('.');
        if (index < 0 || index == fileName.length() - 1) {
            throw new IllegalArgumentException("fileName has no extension: " + fileName);
        }
        selectParserByFormat(fileName.substring(index + 1).toLowerCase());
    }

    @Override
    public ArrayList<Question> parse(String fileName) {
        if (parser == null) {
            throw new IllegalStateException("No parser initialized");
        }
        return parser.parse(fileName);
    }

    private void selectParserByFormat(String format) {
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

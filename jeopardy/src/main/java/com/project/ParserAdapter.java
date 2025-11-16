package com.project;

import java.util.ArrayList;

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
    public ArrayList<Category> parse(String fileName) {
        if (parser == null) {
            throw new IllegalStateException("No parser initialized");
        }
        return parser.parse(fileName);
    }

    private void selectParserByFormat(String format) {
        switch (format.toLowerCase()) {
            case "csv":
                this.parser = new CSVParser();
                break;
            case "json":
                this.parser = new JSONParser();
                break;
            case "xml":
                this.parser = new XMLParser();
                break;
            default:
                throw new IllegalArgumentException("Unsupported file format: " + format);
        }
    }
}

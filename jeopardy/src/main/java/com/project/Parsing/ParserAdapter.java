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

    /**
     * Create a ParserAdapter for the given filename. The adapter chooses a
     * concrete parser implementation based on the file extension. Actual IO
     * exceptions are deferred to the concrete parser during {@link #parse}.
     *
     * @param fileName filename used to infer the parser format (must include an extension)
     * @throws IllegalArgumentException when filename is null, empty, or has no extension
     */
    public ParserAdapter(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("fileName is null or empty");
        }
        int index = fileName.lastIndexOf('.');
        if (index < 0 || index == fileName.length() - 1) {
            throw new IllegalArgumentException("fileName has no extension: " + fileName);
        }
        // Select parser based on extension; do not require the resource to exist
        // at construction time. Existence/IO errors are handled by the concrete
        // parser implementations during `parse`.
        selectParserByFormat(fileName.substring(index + 1).toLowerCase());
    }

    /**
     * Parse the provided file by delegating to the selected format-specific
     * parser implementation.
     *
     * @param fileName path to the input file
     * @return list of parsed {@code Question} objects
     * @throws IllegalStateException when no parser was selected
     */
    @Override
    public ArrayList<Question> parse(String fileName) {
        if (parser == null) {
            throw new IllegalStateException("No parser initialized");
        }
        return parser.parse(fileName);
    }

    /**
     * Select a concrete parser implementation for the given extension.
     * Supported values: "csv", "json", "xml".
     *
     * @param format file extension without leading dot
     * @throws IllegalArgumentException when the format is not supported
     */
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

package com.project.Parsing;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.project.Questions.Question;
import com.project.Questions.QuestionBuilder;

/**
 * CSV parser implementation that reads questions from a CSV file and builds
 * Question instances using the QuestionBuilder.
 */
public class CSVParserAdaptee implements ParserAdaptee {
    private final ArrayList<Question> questions;

    /**
     * Create a new CSV parser instance.
     * The parser holds an internal list of parsed questions and is safe to reuse
     * for multiple parses (each call to {@link #parse(String)} clears previous
     * results).
     */
    public CSVParserAdaptee(){
        this.questions = new ArrayList<>();
    }

    /**
     * Parse questions from a CSV file. The CSV is expected to have a
     * header row followed by rows matching the project format. Malformed
     * rows are logged and skipped.
     *
     * @param fileName file path or resource path identifying the CSV
     * @return list of parsed {@code Question} objects (never null)
     */
    @Override
    public ArrayList<Question> parse(String fileName){
        questions.clear();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try(Reader sourceReader = inputStream != null
                ? new InputStreamReader(inputStream, StandardCharsets.UTF_8)
                : new FileReader(normalizePath(fileName));
            CSVReader reader = new CSVReader(sourceReader)) {
            String[] parsedInfo;
            reader.readNext(); // Skip header
            while((parsedInfo = reader.readNext()) != null){
                if (parsedInfo.length >= 8) {
                    try {
                        QuestionBuilder builder = QuestionBuilder.create();
                        builder.setCategory(parsedInfo[0]);
                        builder.setValue(Integer.parseInt(parsedInfo[1]));
                        builder.setQuestion(parsedInfo[2]);
                        builder.setOptions(new ArrayList<>(Arrays.asList(
                            parsedInfo[3], parsedInfo[4], parsedInfo[5], parsedInfo[6])));
                        builder.setAnswer(parsedInfo[7]);
                        questions.add(builder.build());
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.err.println("Not expected format: " + e.getMessage());
                    } catch (IllegalStateException e) {
                        System.err.println("Builder validation failed: " + e.getMessage());
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file '" + fileName + "': " + e.getMessage());
        }
        return questions;
    }

    /**
     * Normalize a possibly URL-encoded classpath or filesystem path.
     * On Windows this will remove a leading '/' before a drive letter.
     *
     * @param p input path or resource identifier
     * @return decoded filesystem path string
     */
    private static String normalizePath(String p) {
        try {
            String decoded = java.net.URLDecoder.decode(p, java.nio.charset.StandardCharsets.UTF_8.name());
            if (decoded.matches("^/[A-Za-z]:.*")) {
                return decoded.substring(1);
            }
            return decoded;
        } catch (UnsupportedEncodingException e) {
            return p;
        }
    }
}

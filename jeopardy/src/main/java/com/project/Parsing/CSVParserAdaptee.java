package com.project.Parsing;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    public CSVParserAdaptee(){
        this.questions = new ArrayList<>();
    }

    @Override
    public ArrayList<Question> parse(String fileName){
        /**
         * Parse questions from a CSV file. The CSV is expected to have a
         * header row followed by rows matching the project format. Malformed
         * rows are logged and skipped.
         *
         * @param fileName file path or resource path identifying the CSV
         * @return list of parsed Question objects (never null)
         */
        questions.clear();
        String path = normalizePath(fileName);
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] parsedInfo;
            reader.readNext();
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
            System.err.println(e.getMessage());
        }
        return questions;
    }

    private static String normalizePath(String p) {
        try {
            String decoded = java.net.URLDecoder.decode(p, java.nio.charset.StandardCharsets.UTF_8.name());
            // On Windows file URLs may begin with a leading '/' before drive letter
            if (decoded.matches("^/[A-Za-z]:.*")) {
                return decoded.substring(1);
            }
            return decoded;
        } catch (UnsupportedEncodingException e) {
            return p;
        }
    }
}

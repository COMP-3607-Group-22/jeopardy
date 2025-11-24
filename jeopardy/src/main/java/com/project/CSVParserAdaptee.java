package com.project;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVParserAdaptee implements ParserAdaptee {
    private final ArrayList<Question> questions;

    public CSVParserAdaptee(){
        this.questions = new ArrayList<>();
    }

    @Override
    public ArrayList<Question> parse(String fileName){
        questions.clear();
        try(CSVReader reader = new CSVReader(new FileReader(fileName))) {
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
}

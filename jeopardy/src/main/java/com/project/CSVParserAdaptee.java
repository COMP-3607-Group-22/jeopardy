package com.project;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVParserAdaptee implements ParserAdaptee {
    private ArrayList<Question> questions;

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
                        questions.add(init(new Question(), parsedInfo));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.err.println("Not expected format: " + e.getMessage());
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println(e.getMessage());
        }
        return questions;
    }

    public Question init(Question question, String[] parsedInfo){
        question.setCategory(parsedInfo[0]);
        question.setValue(Integer.parseInt(parsedInfo[1]));
        question.setQuestion(parsedInfo[2]);
        question.setOptions(new ArrayList<>(Arrays.asList(parsedInfo[3], parsedInfo[4], parsedInfo[5], parsedInfo[6])));
        question.setAnswer(parsedInfo[7]);
        return question;
    }
}

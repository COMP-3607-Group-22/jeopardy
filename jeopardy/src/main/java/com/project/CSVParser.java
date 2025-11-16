package com.project;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVParser implements ParserAdaptee {
    private ArrayList<Category> categories;

    public CSVParser(){
        this.categories = new ArrayList<>();
    }

    public ArrayList<Category> parse(String fileName){
        try(CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] parsedInfo;
            while((parsedInfo = reader.readNext()) != null){
                categories.add(init(new Category(), parsedInfo));
            }
        } catch (IOException | CsvValidationException | ArrayIndexOutOfBoundsException  e) {
            System.err.println(e.getMessage());
        }
        return categories;
    }

    public Category init(Category category, String[] parsedInfo){
        category.setCategory(parsedInfo[0]);
        category.setValue(Integer.parseInt(parsedInfo[1]));
        category.setQuestion(parsedInfo[2]);
        category.setOptions(new ArrayList<>(Arrays.asList(parsedInfo[3], parsedInfo[4], parsedInfo[5], parsedInfo[6])));
        category.setAnswer(parsedInfo[7]);
        return category;
    }
}

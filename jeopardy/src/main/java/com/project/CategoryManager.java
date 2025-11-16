package com.project;
import java.util.ArrayList;

public class CategoryManager {
    private ArrayList<Category> categories;

    public CategoryManager(){
        categories = new ArrayList<>();
    }

    public void buildQuestions(String fileName){
        FileParser parser = new ParserAdapter(fileName);
        categories = parser.parse(fileName);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}

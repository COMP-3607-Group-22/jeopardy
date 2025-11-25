package com.project.Questions;
import java.util.ArrayList;

import com.project.Parsing.FileParser;
import com.project.Parsing.ParserAdapter;

public class QuestionManager {
    private ArrayList<Question> questions;
    private boolean status;

    public QuestionManager(){
        questions = new ArrayList<>();
    }

    public void buildQuestions(String fileName){
        FileParser parser = new ParserAdapter(fileName);
        questions = parser.parse(fileName);
        status = !questions.isEmpty();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean getStatus(){
        return status;
    }
}

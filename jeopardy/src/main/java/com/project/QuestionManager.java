package com.project;
import java.util.ArrayList;

public class QuestionManager {
    private ArrayList<Question> questions;

    public QuestionManager(){
        questions = new ArrayList<>();
    }

    public boolean buildQuestions(String fileName){
        FileParser parser = new ParserAdapter(fileName);
        questions = parser.parse(fileName);
        return !questions.isEmpty();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}

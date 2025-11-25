package com.project.Categories;
import java.util.ArrayList;

public class Question {
    private String category;
    private int value;
    private String question;
    private ArrayList<String> options;
    private String answer;

    public Question(String category, int value, String question, 
        ArrayList<String> options, String answer){
            this.category = category;
            this.value = value;
            this.question = question;
            this.options = options;
            this.answer = answer;
        }

    public String getCategory(){return this.category;}
    public int getValue(){return this.value;}
    public String getQuestion(){return this.question;}
    public ArrayList<String> getOptions(){return this.options;}
    public String getAnswer(){return this.answer;}

}

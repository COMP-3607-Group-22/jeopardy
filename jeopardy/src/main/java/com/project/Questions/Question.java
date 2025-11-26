package com.project.Questions;
import java.util.ArrayList;

public class Question {
    private final String category;
    private final int value;
    private final String question;
    private final ArrayList<String> options;
    private final String answer;

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

    public String getOptionsAsString(){
        StringBuilder optionsStr = new StringBuilder();
        char optionLabel = 'A';
        for(String option : options){
            optionsStr.append(optionLabel).append(". ").append(option).append("\n");
            optionLabel++;
        }
        return optionsStr.toString();
    }

}

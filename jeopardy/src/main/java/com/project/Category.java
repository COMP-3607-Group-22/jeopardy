package com.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Category {
    private String category;
    private int value;
    private String question;
    private ArrayList<String> options;
    private String answer;

    public String getCategory(){return this.category;}
    public int getValue(){return this.value;}
    public String getQuestion(){return this.question;}
    public ArrayList<String> getOptions(){return this.options;}
    public String getAnswer(){return this.answer;}

    public void setCategory(String category){this.category = category;}
    public void setValue(int value){this.value = value;}
    public void setQuestion(String question){this.question = question;}
    public void setOptions(ArrayList<String> options){this.options = options;}
    public void setAnswer(String answer){this.answer = answer;}
}

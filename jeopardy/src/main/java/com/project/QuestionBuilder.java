package com.project;
import java.util.ArrayList;

public class QuestionBuilder implements Builder {
    private String category;
    private int value;
    private String question;
    private ArrayList<String> options;
    private String answer;

    public static QuestionBuilder create(){
        return new QuestionBuilder();
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setOptions(ArrayList<String> options){
        this.options = options;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    @Override
    public Question build(){
        
        if (category == null || category.isEmpty())
            throw new IllegalStateException("Category is required");
        if (question == null || question.isEmpty())
            throw new IllegalStateException("Question text is required");
        if (answer == null || answer.isEmpty())
            throw new IllegalStateException("Answer is required");
        if (options == null || options.isEmpty())
            throw new IllegalStateException("At least one option is required");
        if (value <= 0)
            throw new IllegalStateException("Value must be positive");
        
        return new Question(
            category,
            value,
            question,
            new ArrayList<>(options),
            answer
        );
    }
}


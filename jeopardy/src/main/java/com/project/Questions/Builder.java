package com.project.Questions;
import java.util.ArrayList;

public interface Builder {
    void setCategory(String category);
    void setValue(int value);
    void setQuestion(String question);
    void setOptions(ArrayList<String> options);
    void setAnswer(String answer);
    Question build();
}

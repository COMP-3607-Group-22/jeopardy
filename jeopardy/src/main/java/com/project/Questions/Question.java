package com.project.Questions;
import java.util.ArrayList;

/**
 * Immutable value object representing a single quiz question.
 */
public class Question {
    private final String category;
    private final int value;
    private final String question;
    private final ArrayList<String> options;
    private final String answer;

    /**
     * Construct an immutable Question value object.
     *
     * @param category category name
     * @param value numeric value/points for the question
     * @param question textual content of the question
     * @param options list of answer options
     * @param answer the correct answer
     */
    public Question(String category, int value, String question, 
        ArrayList<String> options, String answer){
            this.category = category;
            this.value = value;
            this.question = question;
            this.options = options;
            this.answer = answer;
        }

    /**
     * Return the category of this question.
     *
     * @return category string
     */
    public String getCategory(){return this.category;}

    /**
     * Return the point value assigned to this question.
     *
     * @return integer point value
     */
    public int getValue(){return this.value;}

    /**
     * Return the main question text.
     *
     * @return question text
     */
    public String getQuestion(){return this.question;}

    /**
     * Return the list of possible answer options.
     *
     * @return an ArrayList of option strings
     */
    public ArrayList<String> getOptions(){return this.options;}

    /**
     * Return the correct answer for this question.
     *
     * @return correct answer string
     */
    public String getAnswer(){return this.answer;}

    /**
     * Return the options formatted as a labeled multi-line string
     * (A., B., ...).
     *
     * @return formatted options string
     */
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

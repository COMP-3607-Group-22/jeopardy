package com.project.Questions;
import java.util.ArrayList;

/**
 * Builder for creating `Question` instances. Validates required fields
 * before returning a constructed `Question`.
 */
public class QuestionBuilder implements Builder {
    private String category;
    private int value;
    private String question;
    private ArrayList<String> options;
    private String answer;

    public static QuestionBuilder create(){
        /**
         * Create a fresh builder instance.
         *
         * @return a new `QuestionBuilder`
         */
        return new QuestionBuilder();
    }

    @Override
    /**
     * Set the category for the question under construction.
     *
     * @param category category name
     */
    public void setCategory(String category){
        this.category = category;
    }

    @Override
    /**
     * Set the point value for this question.
     *
     * @param value positive integer point value
     */
    public void setValue(int value){
        this.value = value;
    }

    @Override
    /**
     * Set the textual content of the question.
     *
     * @param question question text
     */
    public void setQuestion(String question){
        this.question = question;
    }

    @Override
    /**
     * Provide selectable answer options for the question.
     *
     * @param options list of option strings
     */
    public void setOptions(ArrayList<String> options){
        this.options = options;
    }

    @Override
    /**
     * Set the correct answer for the question.
     *
     * @param answer correct answer text
     */
    public void setAnswer(String answer){
        this.answer = answer;
    }

    @Override
    /**
     * Validate builder state and construct an immutable `Question`.
     *
     * @return constructed `Question`
     * @throws IllegalStateException when required fields are missing or invalid
     */
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


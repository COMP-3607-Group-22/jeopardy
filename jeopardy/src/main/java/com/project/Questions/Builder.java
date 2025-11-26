package com.project.Questions;
import java.util.ArrayList;

/**
 * Generic builder interface for constructing `Question` instances.
 */
public interface Builder {
    /**
     * Set the category for the question being built.
     *
     * @param category the question category name
     */
    void setCategory(String category);

    /**
     * Set the monetary value (points) for the question.
     *
     * @param value positive integer representing question value
     */
    void setValue(int value);

    /**
     * Set the main question text.
     *
     * @param question the question text
     */
    void setQuestion(String question);

    /**
     * Provide the list of selectable answer options for the question.
     *
     * @param options list of option strings (must not be empty)
     */
    void setOptions(ArrayList<String> options);

    /**
     * Set the correct answer text for the question.
     *
     * @param answer the correct answer string
     */
    void setAnswer(String answer);

    /**
     * Validate and construct a `Question` instance from the supplied
     * builder state.
     *
     * @return a fully-built, immutable `Question`
     * @throws IllegalStateException if required fields are missing or invalid
     */
    Question build();
}

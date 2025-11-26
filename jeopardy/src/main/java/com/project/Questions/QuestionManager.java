package com.project.Questions;
import java.util.ArrayList;

import com.project.Parsing.FileParser;
import com.project.Parsing.ParserAdapter;

/**
 * Loads and holds the set of `Question` objects for the game. Delegates
 * parsing to a `FileParser` (via ParserAdapter).
 */
public class QuestionManager {
    private ArrayList<Question> questions;
    private boolean status;
    /**
     * Create a new QuestionManager with an empty question list.
     * The manager's status will be false until questions are loaded.
     */
    public QuestionManager(){
        questions = new ArrayList<>();
        status = false;
    }

    /**
     * Parse and load questions from the provided file.
     * <p>
     * This method delegates actual file parsing to a `FileParser` via
     * `ParserAdapter`. After parsing, the internal question list is
     * replaced and the `status` flag is set to true when at least one
     * question was successfully loaded.
     *
     * @param fileName the path to the question file to load
     */
    public void buildQuestions(String fileName){
        FileParser parser = new ParserAdapter(fileName);
        questions = parser.parse(fileName);
        status = !questions.isEmpty();
    }

    /**
     * Return the list of loaded questions. The returned list may be empty
     * if no questions have been loaded yet.
     *
     * @return an ArrayList of `Question` objects (never null)
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Indicates whether questions were successfully loaded by
     * `buildQuestions`.
     *
     * @return true when at least one question is available; false otherwise
     */
    public boolean getStatus(){
        return status;
    }
}

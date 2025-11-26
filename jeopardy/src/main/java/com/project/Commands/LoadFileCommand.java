package com.project.Commands;

import com.project.Helpers.LogHelper;
import com.project.Questions.QuestionManager;

/**
 * Command that triggers loading of question data from a file into the
 * {@link QuestionManager}.
 */
public class LoadFileCommand implements GameCommand {
    private final String fileName;
    private final QuestionManager questionManager;

    /**
     * Command that loads question data from a file into the QuestionManager.
     *
     * @param questionManager manager responsible for constructing questions
     * @param fileName path or resource name of the input file
     */
    public LoadFileCommand(QuestionManager questionManager, String fileName){
        this.fileName = fileName;
        this.questionManager = questionManager;
    }

    @Override
    public void execute(){
        this.questionManager.buildQuestions(this.fileName);
    }
    /**
     * @return a short log line describing the file load status
     */
    @Override
    public String toString(){
        return "System,Load File," + new LogHelper().getTimeStamp()
        + ",,," + (this.questionManager.getStatus() ? "Success" : "Failure") + ",";
    }
}

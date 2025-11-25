package com.project.Commands;

import com.project.Helpers.LogHelper;
import com.project.Questions.QuestionManager;

public class LoadFileCommand implements GameCommand {
    private final String fileName;
    private final QuestionManager questionManager;

    public LoadFileCommand(QuestionManager questionManager, String fileName){
        this.fileName = fileName;
        this.questionManager = questionManager;
    }

    @Override
    public void execute(){
        this.questionManager.buildQuestions(this.fileName);
    }

    @Override
    public String toString(){
        return "Load File," + new LogHelper().getTimeStamp()
        + ",,," + (this.questionManager.getStatus() ? "Success" : "Failure") + ",";
    }
}

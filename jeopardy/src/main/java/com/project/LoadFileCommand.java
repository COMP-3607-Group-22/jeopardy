package com.project;

public class LoadFileCommand implements GameEngineCommand {
    private String fileName;
    private QuestionManager questionManager;

    public LoadFileCommand(QuestionManager questionManager, String fileName){
        this.fileName = fileName;
        this.questionManager = questionManager;
    }
    @Override
    public void execute(){
        this.questionManager.buildQuestions(this.fileName);
    }
}

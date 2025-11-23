package com.project;

public class LoadFileCommand implements GameEngineCommand {
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
}

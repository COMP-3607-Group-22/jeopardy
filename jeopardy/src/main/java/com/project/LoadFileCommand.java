package com.project;

public class LoadFileCommand implements GameEngineCommand {
    private String fileName;
    private CategoryManager questionManager;

    public LoadFileCommand(CategoryManager questionManager, String fileName){
        this.fileName = fileName;
        this.questionManager = questionManager;
    }
    @Override
    public void execute(){
        this.questionManager.buildQuestions(this.fileName);
    }
}

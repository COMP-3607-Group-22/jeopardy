package com.project;

public class LoadFileCommand {
    private String fileName;
    private CategoryManager questionManager;

    public LoadFileCommand(CategoryManager questionManager, String fileName){
        this.fileName = fileName;
        this.questionManager = questionManager;
    }

    public void execute(){
        this.questionManager.buildQuestions(this.fileName);
    }
}

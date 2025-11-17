package com.project;
public class SelectCategoryCommand implements GameEngineCommand {

    private final String CategoryName;

    public SelectCategoryCommand(String CategoryName){
        this.CategoryName = CategoryName;
    }
    @Override 
    public void execute(){
        System.err.println("");
    }
}

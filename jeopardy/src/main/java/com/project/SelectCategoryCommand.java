package com.project;
public class SelectCategoryCommand implements GameEngineCommand {

    private final String categoryName;
    private final GameEngine gameEngine;

    public SelectCategoryCommand(GameEngine gameEngine, String categoryName){
        this.gameEngine = gameEngine;
        this.categoryName = categoryName;
    }
    @Override 
    public void execute(){
        gameEngine.selectCategory(categoryName);
    }
}

package com.project;
public class SelectCategoryCommand implements GameEngineCommand {

    private final GameEngine gameEngine;

    public SelectCategoryCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        
    }
    @Override 
    public void execute(){
        gameEngine.selectCategory();
    }
}

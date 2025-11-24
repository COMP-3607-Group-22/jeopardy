package com.project;

public class SelectQuestionCommand implements GameEngineCommand {
   
    private final GameEngine gameEngine;
    
    public SelectQuestionCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    
    }

    @Override 
    public void execute() {
        gameEngine.selectQuestion(); 
    }
}

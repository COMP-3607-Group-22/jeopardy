package com.project.Commands;

import com.project.Gameplay.GameEngine;

public class SelectQuestionCommand implements GameCommand {
   
    private final GameEngine gameEngine;
    
    public SelectQuestionCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    
    }

    @Override 
    public void execute() {
        gameEngine.selectQuestion(); 
    }
}

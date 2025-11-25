package com.project.Commands;

import com.project.Gameplay.GameEngine;

public class SelectCategoryCommand implements GameCommand {

    private final GameEngine gameEngine;

    public SelectCategoryCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        
    }
    @Override 
    public void execute(){
        gameEngine.selectCategory();
    }
}

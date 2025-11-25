package com.project.Commands;

import com.project.Gameplay.GameInitialization;

public class SelectPlayerCountCommand implements GameCommand {
    
    private final GameInitialization gameEngine;

    public SelectPlayerCountCommand(GameInitialization gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.selectPlayerCount();
    }
}


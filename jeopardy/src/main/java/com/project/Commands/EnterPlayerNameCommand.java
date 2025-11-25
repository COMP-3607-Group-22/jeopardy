package com.project.Commands;

import com.project.Gameplay.GameInitialization;

public class EnterPlayerNameCommand implements GameCommand{
    private final GameInitialization gameEngine;

    public EnterPlayerNameCommand(GameInitialization gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.enterPlayerName();
    }
}


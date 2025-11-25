package com.project.Commands;

import com.project.Gameplay.GameInitialization;

public class ExitCommand implements GameCommand {

    private final GameInitialization gameEngine;

    public ExitCommand(GameInitialization gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.exitGame();
    }
}
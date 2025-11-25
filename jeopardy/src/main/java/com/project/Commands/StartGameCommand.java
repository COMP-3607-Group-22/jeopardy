package com.project.Commands;

import com.project.Gameplay.GameInitialization;

public class StartGameCommand implements GameCommand {
    private final GameInitialization gameEngine;

    public StartGameCommand(GameInitialization gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.startGame();
    }
}

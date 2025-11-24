package com.project;

public class StartGameCommand implements GameEngineCommand {
    private final GameInitialization gameEngine;

    public StartGameCommand(GameInitialization gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.startGame();
    }
}

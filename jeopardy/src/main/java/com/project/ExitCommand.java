package com.project;

public class ExitCommand implements GameEngineCommand {

    private final GameEngine gameEngine;

    public ExitCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.exitGame();
    }
}
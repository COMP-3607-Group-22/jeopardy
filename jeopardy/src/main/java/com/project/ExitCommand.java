package com.project;

public class ExitCommand implements GameEngineCommand {

    private final GameInitialization gameEngine;

    public ExitCommand(GameInitialization gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.exitGame();
    }
}
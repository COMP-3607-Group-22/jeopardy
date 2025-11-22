package com.project;

public class EnterPlayerNameCommand implements GameEngineCommand{
    private final GameEngine gameEngine;

    public EnterPlayerNameCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.enterPlayerName();
    }
}


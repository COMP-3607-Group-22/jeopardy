package com.project;

public class EnterPlayerNameCommand implements GameEngineCommand{
    private final GameInitialization gameEngine;

    public EnterPlayerNameCommand(GameInitialization gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.enterPlayerName();
    }
}


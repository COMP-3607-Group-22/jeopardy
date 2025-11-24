package com.project;

public class SelectPlayerCountCommand implements GameEngineCommand {
    
    private final GameInitialization gameEngine;

    public SelectPlayerCountCommand(GameInitialization gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.selectPlayerCount();
    }
}


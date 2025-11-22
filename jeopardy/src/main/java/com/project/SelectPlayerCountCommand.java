package com.project;

public class SelectPlayerCountCommand implements GameEngineCommand {
    
    private final GameEngine gameEngine;

    public SelectPlayerCountCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.selectPlayerCount();
    }
}


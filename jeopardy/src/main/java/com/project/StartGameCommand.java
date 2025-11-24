package com.project;

public class StartGameCommand implements GameEngineCommand {
    private final GameEngine gameEngine;

    public StartGameCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.startGame();
    }

    @Override
    public String toString() {
}

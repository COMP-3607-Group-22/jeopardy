package com.project;

public class GenerateReportCommand implements GameEngineCommand {

    private final GameEngine gameEngine;

    public GenerateReportCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.generateReport();
    }
}
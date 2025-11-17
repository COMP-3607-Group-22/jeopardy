package com.project;

public class EventLogReportCommand implements GameEngineCommand {

    private final GameEngine gameEngine;

    public EventLogReportCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.generateEventLog();
    }
}
package com.project;

import java.io.IOException;
import java.util.ArrayList;

public class EventLogReportCommand implements GameEngineCommand {

    private final GameEngine gameEngine;
    private final ArrayList<String> history;

    public EventLogReportCommand(GameEngine gameEngine, ArrayList<String> history) {
        this.gameEngine = gameEngine;
        this.history = history;
    }

    @Override 
    public void execute() {
        try { gameEngine.generateEventLog(history);} 
        catch (IOException e) {}
}
}
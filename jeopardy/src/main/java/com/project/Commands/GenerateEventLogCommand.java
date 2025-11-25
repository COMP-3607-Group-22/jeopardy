package com.project.Commands;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.EventLogHelper;
import com.project.Helpers.LogHelper;

public class GenerateEventLogCommand implements GameCommand {

    private final GameTermination gameTerm;
    private final EventLogHelper eventLogHelper;

    public GenerateEventLogCommand(GameTermination gameTerm, EventLogHelper eventLogHelper) {
        this.gameTerm = gameTerm;
        this.eventLogHelper = eventLogHelper;
    }

    @Override
    public void execute() {
        gameTerm.generateEventLog(eventLogHelper);
    }

    @Override
    public String toString(){
        return "System,Generate Event Log," + new LogHelper().getTimeStamp() + ",,,N/A,";
    }
}
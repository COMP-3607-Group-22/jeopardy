package com.project.Commands;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.EventLogHelper;
import com.project.Helpers.LogHelper;

/**
 * Command that triggers generation of the persistent event log file using the
 * provided EventLogHelper instance.
 */
public class GenerateEventLogCommand implements GameCommand {

    private final GameTermination gameTerm;
    private final EventLogHelper eventLogHelper;

    /**
     * Create the command that will instruct GameTermination to persist the
     * supplied in-memory event log.
     *
     * @param gameTerm GameTermination helper responsible for file outputs
     * @param eventLogHelper in-memory event log to persist
     */
    public GenerateEventLogCommand(GameTermination gameTerm, EventLogHelper eventLogHelper) {
        this.gameTerm = gameTerm;
        this.eventLogHelper = eventLogHelper;
    }

    @Override
    public void execute() {
        gameTerm.generateEventLog(eventLogHelper);
    }

    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "System,Generate Event Log," + new LogHelper().getTimeStamp() + ",,,N/A,";
    }
}
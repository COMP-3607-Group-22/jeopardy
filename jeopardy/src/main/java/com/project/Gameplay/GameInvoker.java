package com.project.Gameplay;

import java.util.ArrayList;

import com.project.Commands.GameCommand;
import com.project.Helpers.EventLogHelper;
import com.project.Helpers.ReportHelper;

/**
 * Central invoker used to execute game commands and record event logs.
 * Records a log entry before and after executing each command.
 */
public class GameInvoker{
    private final EventLogHelper eventLogHelper = new EventLogHelper();
    private final String caseId;

    /**
     * Create a GameInvoker which records commands and annotates log entries
     * with the provided case identifier.
     *
     * @param caseId case identifier used as a prefix on all log entries
     */
    public GameInvoker(String caseId){
        this.caseId = caseId;
    }

    /**
     * Execute a command and record pre/post entries in the event log.
     *
     * @param command the GameCommand to execute
     */
    public void executeCommand(GameCommand command){
        int index = eventLogHelper.logEvent(createLogEntry(command));
        command.execute();
        eventLogHelper.updateEventLog(index, createLogEntry(command));
    }

    /**
     * Create a CSV-style log entry that prefixes the case id.
     *
     * @param command command whose textual representation will be appended
     * @return formatted log entry
     */
    public String createLogEntry(GameCommand command){
        return this.caseId + "," + command.toString();
    }

    /**
     * Return the case identifier associated with this invoker.
     *
     * @return the case identifier assigned to this invoker
     */
    public String getCaseId(){return this.caseId;}

    /**
     * Return the EventLogHelper used to record pre/post command entries.
     *
     * @return EventLogHelper instance used by this invoker
     */
    public EventLogHelper getEventLogHelper(){return this.eventLogHelper;}

    /**
     * Create a ReportHelper for the provided players and ensure it is
     * annotated with this invoker's case id. This centralizes case id
     * propagation so callers don't need to set it manually.
     *
     * @param players list of players for the report
     * @return a ReportHelper instance with the case id set
     */
    public ReportHelper createReportHelper(ArrayList<Player> players){
        ReportHelper rh = new ReportHelper(players, null);
        rh.setCaseId(this.caseId);
        return rh;
    }
}

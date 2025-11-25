package com.project.Gameplay;

import com.project.Commands.GameCommand;
import com.project.Helpers.EventLogHelper;

public class GameInvoker{
    private EventLogHelper eventLogHelper = new EventLogHelper();
    private final String caseId;

    public GameInvoker(String caseId){
        this.caseId = caseId;
    }

    public void executeCommand(GameCommand command){
        int index = eventLogHelper.logEvent(createLogEntry(command));
        command.execute();
        eventLogHelper.updateEventLog(index, createLogEntry(command));
    }

    public String createLogEntry(GameCommand command){
        return this.caseId + "," + command.toString();
    }

    public String getCaseId(){return this.caseId;}
    public EventLogHelper getEventLogHelper(){return this.eventLogHelper;}
}

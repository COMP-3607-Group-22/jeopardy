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
        addEventLog(command);
        command.execute();
        updateEventLog(command);
    }

    public void addEventLog(GameCommand command){
        eventLogHelper.logEvent(this.caseId + "," + command.toString());
    }

    public void updateEventLog(GameCommand command){
        eventLogHelper.updateEventLog(this.caseId + "," + command.toString());
    }

    public String getCaseId(){return this.caseId;}
    public EventLogHelper getEventLogHelper(){return this.eventLogHelper;}
}

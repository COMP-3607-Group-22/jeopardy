package com.project.Helpers;
import java.util.ArrayList;

public class EventLogHelper {
    ArrayList<String> eventLogs;

    public EventLogHelper() {
        eventLogs = new ArrayList<>();
        eventLogs.add("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");
    }

    public void logEvent(String event) {
        eventLogs.add(event);
    }

    public void updateEventLog(String newEvent){
        eventLogs.set(eventLogs.size()-1, newEvent);
    }

    public ArrayList<String> getEventLogs() {
        return eventLogs;
    }
}

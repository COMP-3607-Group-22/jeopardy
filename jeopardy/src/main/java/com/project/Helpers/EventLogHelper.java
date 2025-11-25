package com.project.Helpers;
import java.util.ArrayList;

public class EventLogHelper {
    ArrayList<String> eventLogs;

    public EventLogHelper() {
        eventLogs = new ArrayList<>();
        eventLogs.add("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");
    }

    public int logEvent(String event) {
        eventLogs.add(event);
        return eventLogs.size() - 1;
    }

    public void updateEventLog(int index, String newEvent){
        eventLogs.set(index, newEvent);
    }

    public ArrayList<String> getEventLogs() {
        return eventLogs;
    }
}

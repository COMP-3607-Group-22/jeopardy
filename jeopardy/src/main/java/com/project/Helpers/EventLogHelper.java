package com.project.Helpers;
import java.util.ArrayList;

/**
 * In-memory event log collector used during gameplay to record actions.
 * Provides simple append/update and retrieval methods consumed by termination logic.
 */
public class EventLogHelper {
    ArrayList<String> eventLogs;

    /**
     * Construct an in-memory event log helper and initialize the CSV header
     * line used by exporters.
     */
    public EventLogHelper() {
        eventLogs = new ArrayList<>();
        eventLogs.add("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");
    }

    /**
     * Append a new event line to the in-memory log.
     *
     * @param event the event string to append (CSV-formatted row expected)
     * @return the index at which the event was stored
     */
    public int logEvent(String event) {
        eventLogs.add(event);
        return eventLogs.size() - 1;
    }

    /**
     * Replace an existing event entry at the given index.
     *
     * @param index the index of the event to replace (0-based)
     * @param newEvent the new event string to place at the index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void updateEventLog(int index, String newEvent){
        eventLogs.set(index, newEvent);
    }

    /**
     * Retrieve the full list of recorded event lines. The first line is
     * the CSV header added at construction.
     *
     * @return an ArrayList containing all event lines (header + entries)
     */
    public ArrayList<String> getEventLogs() {
        return eventLogs;
    }
}

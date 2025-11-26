package com.project;

import org.junit.jupiter.api.Test;
import com.project.Gameplay.GameInvoker;
import com.project.Helpers.EventLogHelper;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("EventLogger Tests")
class EventLoggerTest {

    private GameInvoker gameInvoker;
    private EventLogHelper eventLogHelper;

    @BeforeEach
    void setup() {
        gameInvoker = new GameInvoker("CASE-001");
        eventLogHelper = gameInvoker.getEventLogHelper();
    }

    @Test
    @DisplayName("Event log starts with correct headers")
    void logStartsWithHeaders() {
        List<String> logs = eventLogHelper.getEventLogs();
        assertEquals(1, logs.size());
        assertTrue(logs.get(0).contains("Case_ID"));
        assertTrue(logs.get(0).contains("Timestamp"));
        assertTrue(logs.get(0).contains("Score_After_Play"));
    }

    @Test
    @DisplayName("Events are logged and updated correctly")
    void eventsAreLoggedAndUpdated() {
        int index = eventLogHelper.logEvent("CASE-001,P1,Start Game,,,,,0");
        assertEquals(2, eventLogHelper.getEventLogs().size());

        eventLogHelper.updateEventLog(index, "CASE-001,P1,End Game,,,,,100");
        String updatedLog = eventLogHelper.getEventLogs().get(index);
        assertTrue(updatedLog.contains("End Game"));
        assertTrue(updatedLog.contains("100"));
    }

    @Test
    @DisplayName("Event log records all game events correctly")
    void eventLogRecordsAllEvents() {
        eventLogHelper.logEvent("CASE-001,P1,Start Game,,,,,,0");
        eventLogHelper.logEvent("CASE-001,P1,Select Category,,Arrays,400,,,0");
        eventLogHelper.logEvent("CASE-001,P1,Answer Question,,Arrays,400,ArrayList,Correct,400");

        List<String> logs = eventLogHelper.getEventLogs();
        String content = String.join("\n", logs);

        assertTrue(content.contains("CASE-001"));
        assertTrue(content.contains("Arrays"));
        assertTrue(content.contains("ArrayList"));
        assertTrue(content.contains("Correct"));
        assertTrue(content.contains("400"));
    }
}

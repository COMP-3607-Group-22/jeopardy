package com.project.Gameplay;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.project.Helpers.EventLogHelper;

public class GameTermination{
    public void generateEventLog(EventLogHelper eventLogHelper){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy/src/main/resources/game_event_log.csv"))) {
            for (String[] row : parseHistoryToData(eventLogHelper.getEventLogs())) {
                for(int i=0; i<row.length; i++){
                    if(row[i] == null){
                        row[i] = "";
                    }
                }
                writer.write(String.join(",", row));
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public void generateReport(ArrayList<String> report){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy/src/main/resources/game_report.txt"))) {
            for (String row : report) {
                writer.write(row);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void exitGame(){
        System.exit(0);
    }

    public String[][] parseHistoryToData(ArrayList<String> history) {
        String[][] data = new String[history.size()][9];
        for (int i = 0; i < history.size(); i++) {
            String logEntry = history.get(i);
            String[] fields = logEntry.split(",");
            System.arraycopy(fields, 0, data[i], 0, Math.min(fields.length, 9));
        }
        return data;
    }
}
